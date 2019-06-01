![HH Logo](http://www.haaga-helia.fi/sites/all/themes/haagahelia/images/logo.png)

# SWD4TN020 - Palvelinohjelmointi (Server programming)

My solutions to various course exercises related to

- Java, Java servlets, JSP files
- Maven
- Spring
- MySQL/MariaDB databases

For the frontends, I used the following libraries and frameworks

- jQuery
- Bootstrap
- [Bootstrap datetimepicker](https://github.com/Eonasdan/bootstrap-datetimepicker/)
- Chart.js
- Moment.js

Course material:
[jleikko/jee](https://github.com/jleikko/jee)

**The programs were created with Eclipse, so I recommend using it if you want to try them out. Make sure to include Tomcat when you build them. The user interfaces are mainly in Finnish, but can easily be translated.**

```sql
CREATE DATABASE SWD4TN020;
USE SWD4TN020;
GRANT ALL PRIVILEGES ON SWD4TN020 TO 'SWD4TN020' IDENTIFIED BY 'SWD4TN020';
```

## h1-aanestys *(voting)*

A yes/no voting application with one question active at a time. Check out websocket_voting in my [java_exercises repository](https://github.com/TatuArvela/java-exercises) for a newer, more practical take on this application.

## h2-vessapaperi *(toilet paper)*

An application for tracking whose turn it is to buy toilet paper. The program goes through the list of tenants one by one, removing paid turns as it goes. It will stop once it lands on a tenant with no paid turns.

A tenant must buy a pack of six rolls of toilet paper on their turn. If they buy economy packs with several packs of six, they can increase their prepaid turns counter by the amount of extra packs they bought.

## h3-korttiohjelmisto *(card application)*

Address card application.

## h4-pistetieto *(score information)*

A course assignment score application.

## h5-painonhallinta *(weight watching)*

A weight watching application. Includes a graph that is generated with Chart.js.

## h6-tyotunnit *(working hours)*

A working hours logging application. Check out my [workingtimes repository](https://github.com/TatuArvela/workingtimes) for a second take on this application made with Node.js and React.

## h7-sulkiskuppi *(shuttlecock cup)*

An application for logging shuttlecock cup results. An empty player object is created in the database to signify an empty player slot.

## h8-sanat *(words)*

An application for listing words in different languages that you want to learn. Has basic authorization and authentication.

## viikkotentti2 *(weekly test 2)*

An application for managing henkilo (people) data. Appears to be compatible with the same database as h3-korttiohjelmisto.

## viikkotentti3 *(weekly test 3)*

Calculator and online time test.

## viikkotentti4 *(weekly test 4)*

An application that lists album data from a database in the console. The entry point is *com.example.viikkotentti4.batch.AlbumilistaHandler*

## viikkotentti5 *(weekly test 5)*

Another application for managing henkilo (people) data. Appears to be compatible with the same database as h3-korttiohjelmisto. Contains basic unit tests.

## viikkotentti6 *(weekly test 6)*

Internationalization and bean validation.
