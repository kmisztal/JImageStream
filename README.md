# JImageStream

## Getting started

### How to start

Clone repository with
```
git clone https://github.com/kmisztal/JImageStream.git
```

Checkout release branch with 
```
git checkout release/1.1
```

Run `mvn clean install` to install project to your local maven repository

Run `mvn clean install -P generate-docs` to generate documentation

Run `mvn clean install -P release` to release jar with sources


### Quick start
Add these dependecies to your pom.xml

    <dependency>
       <groupId>pl.edu.uj</groupId>
       <artifactId>jis-api</artifactId>
        <version>1.1</version>
    </dependency>
    <dependency>
        <groupId>pl.edu.uj</groupId>
        <artifactId>jis-plugins</artifactId>
        <version>1.1</version>
    </dependency>

OR

Use jitpack to resolve JImageStream dependencies

    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>com.github.kmisztal</groupId>
            <artifactId>JImageStream</artifactId>
            <version>1.1</version>
        </dependency>
    </dependencies>

### Example use
   
```	
	new StreamableImage(new File("input.png"))
				.stream()
                .apply(new GrayScaleFilter())
				.collect(new FileCollector("png", "output.png"));
```


### Documentation
https://kmisztal.github.io/JImageStream/
