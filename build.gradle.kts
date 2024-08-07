plugins {
  id("fabric-loom") version "1.7-SNAPSHOT"
  id("maven-publish")
}

fun get(name: String): String {
  return project.properties[name].toString()
}

val artifactId = get("artifact_id")

val modId = get("mod_id")

val minecraftVersion = get("minecraft_version")

val yarnMappingsVersion = get("loom_yarn_mappings_version")
val fabricLoaderVersion = get("loom_fabric_loader_version")
val fabricApiVersion = get("loom_fabric_api_version")

val githubPackagesUrl = get("github_packages_url")

group = get("project_group")
version = get("project_version")

base {
  archivesName = "${artifactId}-fabric-${minecraftVersion}"
}

dependencies {
  minecraft("com.mojang:minecraft:${minecraftVersion}")
  mappings("net.fabricmc:yarn:${yarnMappingsVersion}:v2")
  modImplementation("net.fabricmc:fabric-loader:${fabricLoaderVersion}")
  modImplementation("net.fabricmc.fabric-api:fabric-api:${fabricApiVersion}")
}

loom {
  splitEnvironmentSourceSets()

  mods {
    create(modId) {
      sourceSet("main")
    }
  }
}

java {
  withSourcesJar()
}

publishing {
  publications {
    create<MavenPublication>("mavenJava") {
      artifactId = base.archivesName.get()
      from(components["java"])
    }
  }

  repositories {
    maven {
      name = "Mods"
      url = uri(layout.buildDirectory.dir("repo"))
    }
    maven {
      name = "GithubPackages"
      url = uri(githubPackagesUrl)
      credentials {
        username = System.getenv("GITHUB_ACTOR")
        password = System.getenv("GITHUB_TOKEN")
      }
    }
  }
}
