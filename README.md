# Codin Game - Spring Challenge 2021
My modest contribution the <a href=https://www.codingame.com/contests/spring-challenge-2021>Spring Challenge 2021</a>.

# What interesting here ?
## My Maven configuration (pom.xml)
I used a <a href=https://github.com/tiramon/CGFileMerge>CGFileMerge of tiramon</a> to merge all my java classes

And configured to allow command mvn exec:exec

Very usefull when used with the Chrome extension <a href=https://chrome.google.com/webstore/detail/codingame-sync-ext/ldjnbdgcceengbjkalemckffhaajkehd>CodinGame Sync - Ext</a> 

### Clean project
    mvn clean

### Download dependencies
    mvn install

### Launch unit tests
    mvn test

### Merge java file in live
A file codingame.java is created at the root

    mvn exec:exec

