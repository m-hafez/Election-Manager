# Election Manager [![Version 2.0](https://img.shields.io/badge/version-2.0-brightgreen.svg?style=flat)](https://github.com/m-hafez/Electronic-Voting/releases/latest) ![Java Version 11](https://img.shields.io/badge/Java%20Version-11-blue.svg?style=flat&logo=java)
> This is an election manager micro-service which can be addressed as heart of "an electronic voting system". This micro-service had been built using series of RESTful APIs which has been developed via Spring Boot Framework. The purpose of this micro-service is to generally provide a management upon electronic voting process. The benefit of this system is to have easy and low-cost way that provides maximum participation and is a way to replace the Traditional voting systems in coming days.

<p align="center">
    <br><br>
  <img width="600" height="500" src="https://user-images.githubusercontent.com/29079706/57645825-5f115a00-75d4-11e9-9934-e85ebf34ec83.png">
    <br><br>
</p>

## Release [![Version 2.0](https://img.shields.io/badge/Release%20Version-2.0-brightgreen.svg?style=flat)](https://github.com/m-hafez/Electronic-Voting/releases/latest)
> Version 1.0 released(Apr 14, 2019). 
- Added Get Number Of Votes API 
- Fix DateTime
- Fix URIs
> Version 2.0 released(Jul 11, 2019). 
- <b>Fully FUM-Election compatible</b>
- <b>Dockerized</b> :whale:
- Elections choices storing improved
## Usage
> The examples usage of mentioned APIs are as following:
- <b>Create election</b>
  - URI: '[/elections/save]()'
    - Method: POST
    - body:
        ```
        {
            "name" : "election1",
            "startTime" : "2019-05-05T06:14:07.017+0000",
            "endTime" : "2019-05-06T06:14:07.017+0000" 
        }
        ```
    - Response <b>```200 OK```</b>
        ```
        {
            "message": "successful"
        }
        ```
- <b>Edit election</b>
  - URI: '[/elections/update]()'
    - Method: PUT
    - body:
        ```
        {
            "id":"1",
            "name" : "election1",
            "startTime" : "2019-05-05T06:14:07.017+0000",
            "endTime" : "2019-05-06T06:14:07.017+0000" 
        }
        ```
    - Response <b>```200 OK```</b>
        ```
        {
            "message": "successful"
        }
        ```    
- <b>Remove election</b>
  - URI: '[/elections/{electionId}/remove]()'
    - Method: GET
    - Response <b>```200 OK```</b>
        ```
        {
            "message": "successful"
        }
        ```
- <b>Increment total vote number for an election</b>
  - URI: '[/elections/votes/increment]()'
    - :warning: because the provided electionPortal not send electionID, this api every time return successful message and response 200 and not incrementing total vote number for an election but <u>compatible</u> with <b>FUM-Election</b>:exclamation:
    - Method: GET
    - Response <b>```200 OK```</b>
        ```
        {
            "message": "successful"
        }
        ```
  - URI: '[/elections/{electionId}/votes/increment]()'
    - :warning: this api incrementing total vote number for an election but <u>not compatible</u> with <b>FUM-Election</b>:exclamation:
    - Method: PUT
    - Response <b>```200 OK```</b>
        ```
        {
            "message": "successful"
        }
        ```        
- <b>Get list of choices for an election</b>
  - URI: '/elections/{electionId}/choices'
    - Method: GET
    
- <b>Get all elections</b>
  - URI: '[/elections/all]()'
    - Method: GET
    - Response <b>```200 OK```</b>
    - Sample answer
        ```
        {
            "data": [
            {
              "id": 1,
              "name": "election2",
              "startTime": "2019-05-05T06:14:07.000+0000",
              "endTime": "2019-05-05T06:14:07.000+0000"
            }
            ],
            "message": "successful"
        }
        ```      
- <b>Check election exists</b>
  - URI: '[/elections/exists]()'
    - Method: GET
    - Parameter Query
        ```
         electionId : Integer 
        ```
    - Response <b>```200 OK```</b>
        ```
        {
            "message": "successful"
        }
        ```  
- <b>Get election details</b>
  - URI: '[/elections/{electionId}/get]()'
    - Method: GET
    - Response <b>```200 OK```</b>
    - Sample answer
        ```
        {
            "data": {
            "id": 1,
            "name": "election2",
            "startTime": "2019-05-05T06:14:07.000+0000",
            "endTime": "2019-05-05T06:14:07.000+0000"
            },
            "message": "successful"
        }
        ```  
- <b>Creating a choice for an election</b>
  - URI: '[/elections/{electionId}/choices/save]()'
    - Method: POST
    - body:
        ```
        {
            "choice" : "option1"
        }
        ```
    - Response <b>```200 OK```</b>
        ```
        {
            "message": "successful"
        }
        ```    
- <b>Editing a choice for an election</b>
  - URI: '[/elections/{electionId}/choices/{choiceId}/edit]()'
    - Method: PUT
    - body:
        ```
        {
            "choice" : "newOption"
        }
        ```
    - Response <b>```200 OK```</b>
        ```
        {
            "message": "successful"
        }
        ```            
- <b>Removing a choice for an election</b>
  - URI: '[/elections/{electionId}/choices/{choiceId}/remove]()'
    - Method: GET
    - Response <b>```200 OK```</b>
        ```
        {
            "message": "successful"
        }
        ```           
- <b>Get election choices</b>
  - URI: '[/elections/{electionId}/choices/all]()'
    - Method: GET
    - Response <b>```200 OK```</b>
    - Sample answer
        ```
        {
            "data": [
                {
                  "id": 2,
                  "choice": "option1"
                }
            ],
            "message": "successful"
        }        
        ```            
- <b>Get elections one choice</b>
  - URI: '[/elections/{electionId}/choices/{choiceId}/get]()'
    - Method: GET
    - Response <b>```200 OK```</b>
        ```
        {
            "message": "successful"
        }
        ```              
- <b>Heartbeat</b>
  - URI: '[/heartbeat]()'
    - This is used to check if the service is up
    - Method: GET
    - Response <b>```200 OK```</b>
- <b>Get number of votes</b>
  - URI: '[/elections/{electionId}/votes]()'
    - Method: GET
    - Sample answer
        ```
        {
            "data": {
                "numberOfVotes": 0
            },
            "message": "successful"
        }
        ```  
