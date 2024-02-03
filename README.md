# Dryuf OneJarLoader

ClassLoader which loads jar files from top level jar and adds them as dependencies - it allows distributing java
application as single jar.


## Release

```xml
<dependency>
	<groupId>net.dryuf</groupId>
	<artifactId>dryuf-onejarloader</artifactId>
	<version>1.0.1</version>
</dependency>
```


## Usage

Create a `main()` function wrapper which initializes `OneJarLoader` `ClassLoader` and executes the true `main()` class:

```java
public class JarMyApplication
{
        public static void main(String[] args)
        {
                OneJarLoader cl = new OneJarLoader();
                cl.invokeMain(JarMyApplication.class.getName() + ".MyApplication", args);
        }
}
```


Build a single jar with main class `JarMyApplication`, for example using `maven-assembly-plugin`:

```xml
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-assembly-plugin</artifactId>
				<version>${maven-assembly-plugin.version}</version>
				<executions>
					<execution>
						<id>uber</id>
						<phase>package</phase>
						<goals>
							<goal>single</goal>
						</goals>
						<configuration>
							<archive>
								<manifest>
									<addClasspath>true</addClasspath>
									<mainClass>myproject.JarMyApplication</mainClass>
								</manifest>
							</archive>
							<finalName>${project.name}</finalName>
							<appendAssemblyId>false</appendAssemblyId>
							<descriptors>
								<descriptor>src/main/assembly/uber.xml</descriptor>
							</descriptors>
						</configuration>
					</execution>
				</executions>
			</plugin>
```

`src/main/assembly/uber.xml` example:
```xml
<assembly
	xmlns="http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.3"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.3 http://maven.apache.org/xsd/assembly-1.1.3.xsd">
	<id>uber</id>
	<formats>
		<format>jar</format>
	</formats>
	<includeBaseDirectory>false</includeBaseDirectory>
	<dependencySets>
		<dependencySet>
			<outputDirectory>/</outputDirectory>
			<useProjectArtifact>true</useProjectArtifact>
			<unpack>false</unpack>
			<scope>runtime</scope>
			<excludes>
				<exclude>net.dryuf:dryuf-onejarloader</exclude>
			</excludes>
		</dependencySet>
		<dependencySet>
			<outputDirectory>/</outputDirectory>
			<useProjectArtifact>true</useProjectArtifact>
			<unpack>true</unpack>
			<scope>runtime</scope>
			<includes>
				<include>net.dryuf:dryuf-onejarloader</include>
			</includes>
		</dependencySet>
	</dependencySets>
	<fileSets>
		<fileSet>
			<directory>${project.build.directory}/classes/</directory>
			<outputDirectory>/</outputDirectory>
			<includes>
				<include>myproject/JarMyApplication.class</include>
			</includes>
			<useDefaultExcludes>true</useDefaultExcludes>
		</fileSet>
	</fileSets>
</assembly>
```

Additionally, you can add `dryuf-executable-jar-maven-plugin` to `pom.xml` to prepend `#!/usr/bin/env -S java -jar` to
built executable:

```xml
			<plugin>
				<groupId>net.dryuf.maven.plugin</groupId>
				<artifactId>dryuf-executable-jar-maven-plugin</artifactId>
                <version>${dryuf-executable-jar-maven-plugin.version}</version>
				<executions>
					<execution>
						<phase>package</phase>
						<goals>
							<goal>create-executable</goal>
						</goals>
						<configuration>
							<vmParams>-Xmx256m</vmParams>
							<sort>true</sort>
							<resourceConfigs>
								<resourceConfig>
									<pattern>glob:**</pattern>
									<type>dir</type>
									<remove>true</remove>
								</resourceConfig>
								<resourceConfig>
									<pattern>glob:*.jar</pattern>
									<minimalCompress>100</minimalCompress>
								</resourceConfig>
							</resourceConfigs>
							<input>${project.build.directory}/${project.name}.jar</input>
						</configuration>
					</execution>
				</executions>
			</plugin>
```

You can then distribute and execute the command simply by `target/myproject` (or `myproject` if in `PATH`).


## Features

The following is supported:
1. Loading classes.
2. Loading resources.
3. Loading native libraries from `<os>/<arch>/the-lib.ext` or `the-lib.ext`
4. Nesting JAR files indefinitely.


## Performance

Extracting JAR files at the beginning runs in parallel, so more CPUs can bring benefit.

Test on 67 MB application on 4-core (8 hyperthreads) Intel 1185G7 or Graviton-3 showed 90-110 ms for the initialization.


## Examples

Examples can be found in [examples directory](examples/).


## License

The code is released under version 2.0 of the [Apache License][].


## Authors

Zbynek Vyskovsky - kvr000@gmail.com and https://github.com/kvr000/ and https://github.com/dryuf/ and
https://www.linkedin.com/in/zbynek-vyskovsky/ .

Originally inspired by JarClassLoader but completely rewritten with Java 8 features and improved performance in mind.


[Apache License]: http://www.apache.org/licenses/LICENSE-2.0

<!--- vim: set tw=120: --->
