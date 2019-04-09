# Election Manager [![Version 1.0](https://img.shields.io/badge/version-1.0-brightgreen.svg?style=flat&logo=appveyor)](https://github.com/m-hafez/Electronic-Voting/releases/latest) 
> This is an election manager micro-service which can be addressed as heart of "an electronic voting system". This micro-service had been built using series of RESTful APIs which has been developed via Spring Boot Framework. The purpose of this micro-service is to generally provide a management upon electronic voting process. The benefit of this system is to have easy and low-cost way that provides maximum participation and is a way to replace the legacy voing systems in coming days.
---
<p align="center">
  <img width="400" height="300" src="https://cdn.xdevelop.ir/img/projects/restful/Electronic_Voting.png">
</p>
---
## Usage
> The examples usage of mentioned APIs are as following:
- Create Election
  - URI: 'http://localhost:8013/elections/create'
    - body:
    ```
    {
      "title": "electionTitle",
      "startTime": "1398/02/18@18:00:00",
      "endTime": "1398/02/18@23:00:00",
      "listOfChoices": "ch1,ch2,ch3",
      "numberOfVotes": "0"
    }
    ```
- Edit Election
  - URI: 'http://localhost:8013/elections/edit'
    - body:
    ```
    {
        "id": 1,
        "title": "election1",
        "startTime": "1398/02/18@18:00:00",
        "endTime": "1398/02/18@23:00:00",
        "listOfChoices": "ch1,ch2",
        "numberOfVotes": "0"
    }
    ```
- Remove Election
  - URI: 'http://localhost:8013/elections/{electionId}/remove'
    - example:
    ```
    http://localhost:8013/elections/1/remove
    ``` 
- Incremenet number of votes
  - URI: 'http://localhost:8013/elections/{electionId}/votes/incremenet'
    - example:
    ```
    http://localhost:8013/elections/1/votes/incremenet
    ```
- Get list of choices
  - URI: 'http://localhost:8013/elections/{electionId}/choices'
    - example:
    ```
    http://localhost:8013/elections/1/choices
    ```
- Get all elections
  - URI: 'http://localhost:8013/elections'
- Check election exists
  - URI: 'http://localhost:8013/elections/{electionId}/exists'
    - example:
    ```
    http://localhost:8013/elections/1/exists
    ```
- Get election details
  - URI: 'http://localhost:8013/elections/{electionId}'
    - example:
    ```
    http://localhost:8013/elections/1
    ```  
