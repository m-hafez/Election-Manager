# Electronic-Voting
> This is an electronic voting micro-service using series of RESTful APIs which has been developed in Spring Boot Framework. The purpose of this micro-service is to generally provide a management upon electronic voting process. The benefit of this system is to have easy and low-cost way that provides maximum participation and is a way to replace the legacy voing systems in coming days.

## Usage
> The examples usage of mentioned APIs are as following:
- Create Election
  - URI: 'http://localhost:8013/election/create'
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
  - URI: 'http://localhost:8013/election/edit'
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
  - URI: 'http://localhost:8013/election/remove'
    - param:
    ```
    electionId
    ```
    usage with GET method
    ```
    http://localhost:8013/election/remove?electionId=1
    ```   
- Incremenet number of votes
  - URI: 'http://localhost:8013/election/incremenet-number-of-votes'
    - param:
    ```
    electionId
    ```
    usage with GET method
    ```
    http://localhost:8013/election/incremenet-number-of-votes?electionId=1
    ```
- Get list of choices
  - URI: 'http://localhost:8013/election/get-list-of-choices'
    - param:
    ```
    electionId
    ```
    usage with GET method
    ```
    http://localhost:8013/election/get-list-of-choices?electionId=1
    ```
- Get all elections
  - URI: 'http://localhost:8013/election/get-all'
- Check election exists
  - URI: 'http://localhost:8013/election/exists'
    - param:
    ```
    electionId
    ```
    usage with GET method
    ```
    http://localhost:8013/election/exists?electionId=1
    ```
- Get election details
  - URI: 'http://localhost:8013/election/get-details'
    - param:
    ```
    electionId
    ```
    usage with GET method
    ```
    http://localhost:8013/election/get-details?electionId=1
    ```      
