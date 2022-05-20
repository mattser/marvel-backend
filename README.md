![Marvel](https://cdn.iconscout.com/icon/free/png-256/marvel-282124.png) 
# Back End (Tech Test 18)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![GCP](https://img.shields.io/badge/Google_Cloud-4285F4?style=for-the-badge&logo=google-cloud&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)


Specification
=============

* A Character API based on the [Marvel API](http://developer.marvel.com/)
* Serves Multiple Endpoint
* Endpoint for retrieving a list off all the character IDs
* Endpoint for retrieving a specific character by an ID
* Endpoint for retrieving a specific character by an ID translated into another language
* [OpenAPI Scheema Doucmentation](./OpenAPI/MarvelAPI.yaml)


IMPORTANT
=========

This is a java spring boot application and can therefore be hosted or run locally. 

**API KEYS ARE REQUIRED FOR THIS APP TO WORK**

This is done by:
* Create a file in src > main > java > resources and call it `keys.properties`
* Create 3 properties with the name:
  * `key.marvel.public =` your Marvel developer public API key
  * `key.marvel.private =` your Marvel developer private API key
  * `key.google =` your Google Cloud API key (Translation Service)

To Use
======

The endpoints include:

* `/characters` Returns a list of all character IDs
* `/characters/{id}` Returns information of a specific character with supplied character `id`
* `/characters/{id}/{language code}` Returns information of a specific character with supplied character `id` and `language code` in `ISO-639-1`

Constraints
===========

This application is a Java Spring Application. All dependencies are handled by Maven. To install neccessary dependencies when ran locally righclick `pom.xml > maven > reload project`.


