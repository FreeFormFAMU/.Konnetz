# .Connectz
COP3060 Class project

A dynamic community application designed to bring together individuals with diverse skills, enabling them to monetize their expertise. This platform serves as a digital marketplace where users can advertise the services they offer and set their own prices.

The app’s primary function is to facilitate the discovery of various skills. It incorporates a robust search feature that allows users to find the exact skill set they need. Whether you’re looking for a graphic designer, a software developer, or a language tutor, this app connects you with the right professional.
In addition to its search functionality, the app includes direct messaging capabilities, fostering seamless communication between users. This feature ensures that all details regarding a job can be discussed and agreed upon directly within the app.Moreover, the app integrates a scheduling feature, allowing users to manage their bookings efficiently. This ensures that both parties are clear about the timing and duration of the job.

To sustain the platform, the app deducts a small fee based on the agreed-upon job price. This fee helps maintain and improve the app’s features, ensuring it continues to serve as a reliable platform for skill exchange. In essence, this community application is more than just a platform; it’s a tool that empowers individuals to monetize their skills, fosters collaboration, and promotes a culture of shared growth and learning. It’s not just about finding a job; it’s about building a community.
![processed-B9DA4F67-6BF4-436B-933B-BEC8482696EC](https://github.com/FreeFormFAMU/.github/assets/96313489/496c3cb1-ce0c-4a27-bbd3-08a64b89aeca)


https://github.com/FreeFormFAMU/.Konnetz/assets/96313489/aff47ce5-a77e-40ee-84e9-63429eebfd78



## Schema


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









