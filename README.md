# Election Manager [![Version 1.0](https://img.shields.io/badge/version-1.0-brightgreen.svg?style=flat)](https://github.com/m-hafez/Electronic-Voting/releases/latest) ![Java Version 11](https://img.shields.io/badge/Java%20Version-11-blue.svg?style=flat&logo=java)
> This is an election manager micro-service which can be addressed as heart of "an electronic voting system". This micro-service had been built using series of RESTful APIs which has been developed via Spring Boot Framework. The purpose of this micro-service is to generally provide a management upon electronic voting process. The benefit of this system is to have easy and low-cost way that provides maximum participation and is a way to replace the legacy voting systems in coming days.

<p align="center">
    <br><br>
  <img width="600" height="500" src="https://cdn.xdevelop.ir/img/projects/restful/Electronic_Voting.png">
    <br><br>
</p>

## Release [![Version 1.0](https://img.shields.io/badge/Release%20Version-1.0-brightgreen.svg?style=flat)](https://github.com/m-hafez/Electronic-Voting/releases/latest)
> Version 1.0 released. 
- Added Get Number Of Votes API 
- Fix DateTime
- Fix URIs
 
## Usage
> The examples usage of mentioned APIs are as following:
- Create Election
  - URI: 'http://localhost:8042/elections'
    - Method: POST
    - body:
    ```
    {
      "title": "electionTitle",
      "startTime": "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
      "endTime": "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
      "listOfChoices": "ch1,ch2,ch3",
      "numberOfVotes": "0"
    }
    ```
- Edit Election
  - URI: 'http://localhost:8042/elections'
    - Method: PUT
    - body:
    ```
    {
        "id": 1,
        "title": "election1",
        "startTime": "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
        "endTime": "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
        "listOfChoices": "ch1,ch2",
        "numberOfVotes": "0"
    }
    ```
- Remove Election
  - URI: 'http://localhost:8042/elections/{electionId}'
    - Method: DELETE
    - example:
    ```
    http://localhost:8042/elections/1
    ``` 
- Incremenet number of votes
  - URI: 'http://localhost:8042/elections/{electionId}/votes/incremenet'
    - Method: PUT
    - example:
    ```
    http://localhost:8042/elections/1/votes/incremenet
    ```
- Get list of choices
  - URI: 'http://localhost:8042/elections/{electionId}/choices'
    - Method: GET
    - example:
    ```
    http://localhost:8042/elections/1/choices
    ```
- Get all elections
  - URI: 'http://localhost:8042/elections'
    - Method: GET
- Check election exists
  - URI: 'http://localhost:8042/elections/{electionId}/exists'
    - Method: GET
    - example:
    ```
    http://localhost:8042/elections/1/exists
    ```
- Get election details
  - URI: 'http://localhost:8042/elections/{electionId}'
    - Method: GET
    - example:
    ```
    http://localhost:8042/elections/1
    ```
- Get Number Of Votes
  - URI: 'http://localhost:8042/elections/{electionId}/votes'
    - Method: GET
    - example:
    ```
    http://localhost:8042/elections/1/votes
    ```
