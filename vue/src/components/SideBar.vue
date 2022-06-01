<template>
  <div class="side-bar">
      <div id='nav-user'>
          <img src="@/resources/icons8-settings-50.png" alt="" class='settings-icon'>
          <img src="@/resources/default-user-image.png" alt="" id='profile-icon'>
      </div>
      <div id='user-stats'>
          <ul>
              <li>
                  value <br> posts
              </li>
              <li>
                  value <br> followers
              </li>
              <li>
                  value <br> likes
              </li>
          </ul>
      </div>
      <div class='nav-link' id='make-a-post-link'>
          <img src="@/resources/add_photo_alternate_FILL0_wght400_GRAD0_opsz48.png" alt="">
          <p>create a post</p>
      </div>
      <div class='nav-link' id='posts-link'>
          <img class='nav-bar-icon' src="" alt="">
          <p>posts</p>
      </div>
      <div class='nav-link' id='favorited-link'>
          <img class='nav-bar-icon' src="" alt="">
          <p>favorited</p>
      </div>
      <div class='nav-link' id='likes-link'>
          <img class='nav-bar-icon' src="" alt="">
          <p>likes</p>
      </div>
      <div class='nav-link' id='people-i-follow-link'>
          <img class='nav-bar-icon' src="" alt="">
          <p>people i follow</p>
      </div>
      <div class='nav-link' id='followers-link'>
          <img class='nav-bar-icon' src="" alt="">
          <p>followers</p>
      </div>
  </div>
</template>

<script>

export default {
  name: "side-bar",
  components: {
  }
};
</script>

<style>
.side-bar {
    width: 275px;
    height: 100vh;
    display: flex;
    flex-direction: column;
    background-color: white;
    border-right: 4px black solid;
    align-items: center;
  }

#nav-user {
    display: flex;
    flex-direction: row;
    margin-left: -50px
}

.settings-icon {
    margin-top: 15px;
    margin-right: 10px;
    max-height: 40px;
    max-width: 40px;
}

#profile-icon {
    margin-top: 45px;
    max-width: 150px;
    padding: 0px;
    box-shadow: 1px 1px 25px var(--primary-background-color);
    border-radius: 100%;
}

#user-stats {
    width: 275px;
    margin-left: -40px;
    padding: 0px
}

ul {
    margin: 0px;
    margin-top: 20px;
    display: flex;
    flex-direction: row;
    justify-content: space-around;
}

li {
    list-style: none !important;
    text-align: center;
}

.nav-link {
    display: flex;
}

.nav-bar-icon {
    max-height: 10px
}


</style>