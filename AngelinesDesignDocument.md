
# TE Gram initial layout design
### Feel free to change as needed. Photo assets stored in vue/src/components/
### Shoutout to Angeline for taking the time to put this together

# Front End
## <br> Account page
###### *(Basically like Instagram lol)*
![FrontEndDesign1](vue\src\components\FrontEndDesign1.png)

## <br> Home View - Wide Screen
#### Static Nav Bar on top && bottom
![FrontEndDesign2](vue\src\components\FrontEndDesign2.png)

## <br> Mobile
#### Static Nav Bar on top && bottom
#### Heart = like / Triangle = favorite
![FrontEndDesign3](vue\src\components\FrontEndDesign3.png)

# <br> Backend
## <br> Database
### User/Login Table
- userId
- username
- pass

### <br> User-Account Relational Table
- userId
- accountId

### <br> Account Details Table
- accountId
- profilePic (url? or id?)
- bio

### <br> Created Photos Table
- accountId
- photoID

### <br> Photo Details Table
- photoId
- imgUrl
- caption
- createdDate
- isPrivate

### <br> Likes Table
#### (what if we stored likes in photos as a list of userIds? and used this as an in-between table)
- accountId
- photoId

### <br> Favorites Table
#### (store favorites in account?)
- accountId
- photoId


### <br> Photo Comments
#### (what if we stored comments in photos as a list of userIds? and used this as an in-between table)
#### (Trello only says Admin manage photos and comments though)
- photoId
- commentId

### <br> Comments Table
- commentId
- comments
- accountID

# <br> Endpoints
- GET All photos not private (home)
- GET Photo description + comments
- Your account
    - GET Account details
    - PUT Account details
    - GET All photos incl private
- Other account
    - GET Account details
    - GET All photos
- GET All favorites photos
- POST photo description 
- POST comments 
