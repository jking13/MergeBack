# MergeBack
Simple application that interfaces with CircleBack's web api to merge existing contacts

## Running
`./gradlew run`

## Building
`./gradlew build`

## Testing
`./gradlew test`

## Stack
- java 8
- gradle 
- guice (https://github.com/google/guice/wiki)
- retrofit (http://square.github.io/retrofit/)
- testng

## Why Guice?
Short answer is because dependency injection makes life a whole lot easier, and Spring is overkill for the scope of this application. More information on Guice vs Spring can be found here: https://github.com/google/guice/wiki/SpringComparison
