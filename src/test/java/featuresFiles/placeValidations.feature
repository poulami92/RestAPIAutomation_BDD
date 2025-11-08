Feature: Validation Place API 

@AddGetDeletePlace
Scenario Outline: Verify if Place is added and delete successfully
 
Given Add Place Payload
| lat   | lng   | accuracy  | name   | phoneNo   | address   | types   | website   | language  |
| <lat> | <lng> | <accuracy>| <name> | <phoneNo> | <address> | <types> | <website> | <language>|

When user calls "AddPlaceAPI" with "POST" request
Then API call is success with success code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
When user calls "GetPlaceAPI" with "GET" request
Then verify "<name>" in getPlace response body
Given Delete Place Payload
When user calls "DeletePlaceAPI" with "DELETE" request
Then API call is success with success code 200

Examples:

| lat        | lng       | accuracy | name            | phoneNo            | address                   | types          | website           | language  |
| -38.383494 | 33.427362 | 50       | Frontline house | (+91) 983 893 3937 | 29, side layout, cohen 09 | shoe park,shop | http://google.com | French-IN |
#| -38.383494 | 33.427362 | 50       | Backline house  | (+91) 983 893 3937 | 29, side layout, cohen 09 | shoe park,shop | http://google.com | Spanish   |

@AddPlace
Scenario Outline: Verify if Place is added successfully
 
Given Add Place Payload
| lat   | lng   | accuracy  | name   | phoneNo   | address   | types   | website   | language  |
| <lat> | <lng> | <accuracy>| <name> | <phoneNo> | <address> | <types> | <website> | <language>|

When user calls "AddPlaceAPI" with "POST" request
Then API call is success with success code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"

Examples:

| lat        | lng       | accuracy | name            | phoneNo            | address                   | types          | website           | language  |
| -38.383494 | 33.427362 | 50       | Frontline house | (+91) 983 893 3937 | 29, side layout, cohen 09 | shoe park,shop | http://google.com | French-IN |

