# Connetz: A Social Networking App

Connetz is a social networking app designed for individuals to publicly showcase their skills and portfolios, engage in discussions, chat with others, and monetize their expertise. It aims to foster collaboration and communication among users from diverse backgrounds. 

## App Evaluation

- **Category:** Social Networking
- **Story:** Connetz allows users to promote their talents and portfolios within their professional fields. Users can engage in public discussions, chat with others, share content, and monetize their skills through posts.
- **Market:** This app is designed for everyone, catering to a wide user base and enabling collaboration and communication among individuals from diverse backgrounds.
- **Habit:** Users can use the app at their convenience, whether they are seeking services, events, or organizational opportunities.
- **Scope:** Connetz has planned phases for its development:
  1. Phase one focuses on skill posting and portfolio showcasing.
  2. Phase two targets larger organizations, likely introducing features tailored for their needs.
  3. Phase three caters to individuals seeking specific services, potentially incorporating features for service requests and fulfillment.
 
Product Spec
1. User Stories (Required and Optional)
Required Must-have Stories
* User logs in to access previous chats and preference settings
* Profiles have a chat window to get to communicate.
* Profile pages for each user
* Profiles have a section to upload/view previous work
* Settings (Accesibility, Notification, General, etc.)
Optional Nice-to-have Stories
* Recommended businesses section
* Profile Add-On: Verified reviews from users on the app


2. Screen Archetypes
    • Login
	• Register - User signs up or logs into their account
		○ Upon Download/Reopening of the application, the user is prompted to log in to gain access to their profile information.
		○ …
	• Messaging Screen - Chat for users to communicate (direct 1-on-1)
		○ Message/chat previews that are searchable by keyword
	• Profile Screen
		○ Allows user to upload a photo and fill in information( description and links) that reflects their interests or the services/ events they can provide or provide information about so it can be available to others
	• Search Screen.
		○ Allows user to be able to choose their desired accounts or services/events of preference by way of keywords or tags
		
	• Interests Screen
		○ Let's people select interests by existing tags
		○ others
	• Post creation Screen.
		○ Allows user to add text(caption and tags, images, and a price/option to purchase the service if applicable 
		○ others
	• Draft Screen.
		○ Allows user to see stored, created but unposted (drafted) posts and are able to select and continue where they left off
		○ others
	• Home Screen.
		○ Can see posts and offers/promotions available for accounts followed and recommended accounts/posts on the basis of similar interests 
		
![image](https://github.com/FreeFormFAMU/.Connetz/assets/106197984/06ca16bc-8a56-4aaa-9d57-8776dac4ad74)



4. Navigation
Tab Navigation (Tab to Screen)
* Landing Screen (with collection of customized business profiles)
* Profile
* Settings
* Search
Optional:
* Saved Profile/Posts Collection
Flow Navigation (Screen to Screen)
* Forced Log-in -> Account creation if no log in is available
* Landing Screen -> View Profiles -> Chat
* Profile -> Text field to be modified.
	•	Settings -> Toggle settings

![processed-B9DA4F67-6BF4-436B-933B-BEC8482696EC](https://github.com/FreeFormFAMU/.github/assets/96313489/496c3cb1-ce0c-4a27-bbd3-08a64b89aeca)


https://github.com/FreeFormFAMU/.Konnetz/assets/96313489/aff47ce5-a77e-40ee-84e9-63429eebfd78


# Sprint Cycle 1 Database 
Created the database desgin for the project 

https://github.com/FreeFormFAMU/.Connetz/assets/96313489/dd24993b-e1fc-4b1f-835b-bd0fd816d119

# Sprint Cycle 2 Models and Classes 
In this Sprint we have completed the model classes as well as start on the controlle and services



## Models

## User
| Property | Type | Description |
|-----------------|-----------------|-----------------|
| user_id | Reference | userId Reference |
| username  | String | users unique username |
| email | String | User email | 
| password | String | User password  |
| created_at | TimeStamp | User TimeStamp of created Post |
| updated_at | TimeStamp | User TimeStamp of updated Post |

## Skills
| Property | Type | Description |
|-----------------|-----------------|-----------------|
| skils_id  | Reference | skills_id Reference |
| user_id | Int | user_id Reference |
| skills_name | String | Users skills name |
| skills_description | String | Users skills description |
| created_at | Timestamp | Date value|
| updated_at | Timestamp | Date value |

## Post 
| Property | Type | Description |
|-----------------|-----------------|-----------------|
| post_id | Reference | post_id Refrenece |
| user_id | int | Cloud References |
| content | String | Content information |
| created_at | Timestamp | Date value |
| updated_at | Timestamp | Date value |

## Messages
| Property | Type | Description |
|-----------------|-----------------|-----------------|
| message_id | Reference | message_id Reference |
| sender_id | int | sender_id Reference |
| receiver_id | int | recevicer_id Reference | 
| message_content | String | message content | 
| send_at | Timmestamp | Date value | 
| read_at | Timestamp | Date value | 

## Following
| Property | Type | Description |
|-----------------|-----------------|-----------------|
| following_id | Refernece | following_id Refernece |
| user_id | int | user_id Reference |
| following_user_id | int | following_user_id Reference |

## Followers
| Property | Type | Description |
|-----------------|-----------------|-----------------|
| followers_id | Reference | followers_id Reference |
| user_id | int | Reference |
| follower_user_id | int | Reference |

## Saved Post Table 
| Property | Type | Description |
|-----------------|-----------------|-----------------|
| saved_post_id | Reference | saved_post_id Reference |
| user_id | int | user_id Reference |
| post_id | int | post_id Reference |
| saved_at | Timestamp | Date Value |

## Liked Posts Table 
| Property | Type | Description |
|-----------------|-----------------|-----------------|
| liked_post_id | Reference | liked_post_id Reference |
| user_id | int | user_id Reference |
| post_id | int | post_id Refernece |
| liked_at | Timestamp | Date value |

## Comments Table 
| Property | Type | Description |
|-----------------|-----------------|-----------------|
| comment_id | Reference | comment_id Reference |
| user_id | int | user_id Reference |
| post_id | int | post_id Reference |
| comment_text | String | comment_text string value |
| commented_at | Timestamp | Date value |

## Feed Table 
| Property | Type | Description |
|-----------------|-----------------|-----------------|
| feed_id | Reference | feed_id Reference |
| user_id | int | Reference |
| item_type | string | item_type value |
| item_content | Refernce | photos,videos |
| created_at | Timestamp | Date Value |
| likes_count | int | like_count int value |
| comments_count | int | comments_count int value |


# Networking

Home Screen Feed

  (Read/GET)Query posts randomly based off of saved posts

  (Create/POST) Create a new save on a post

  (Delete) Delete existing save

Create Post Screen

  (Create/POST) Create a new post object

Profile Screen

  (Read/Get) Query logged in user object

  (Update/PUT) Update user profile image

  (Update/PUT) Update user account information









