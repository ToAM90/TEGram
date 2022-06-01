<template>
  <div class="side-bar">
    <div id="nav-user">
      <img
        src="@/resources/icons8-settings-50.png"
        alt=""
        class="settings-icon"
      />
      <img src="@/resources/default-user-image.png" alt="" id="profile-icon" />
    </div>
    <div id="user-stats">
      <ul>
        <li>
          10 <br />
          posts
        </li>
        <li>
          22 <br />
          followers
        </li>
        <li>
          25 <br />
          likes
        </li>
      </ul>
    </div>
    <div class="nav-list">
      <div class="nav-link" id="make-a-post-link">
        <img
          class="nav-bar-icon"
          src="@/resources/add_photo_alternate_FILL0_wght400_GRAD0_opsz48.png"
          alt=""
        />
        <p class="nav-text">create a post</p>
      </div>
      <div class="nav-link" id="posts-link">
        <img
          class="nav-bar-icon"
          src="@/resources/photo_library_FILL0_wght400_GRAD0_opsz48.png"
          alt=""
        />
        <p class="nav-text">posts</p>
      </div>
      <div class="nav-link" id="favorited-link">
        <img
          class="nav-bar-icon"
          src="@/resources/label_important_FILL0_wght400_GRAD0_opsz48.png"
          alt=""
        />
        <p class="nav-text">favorited</p>
      </div>
      <div class="nav-link" id="likes-link">
        <img
          class="nav-bar-icon"
          src="@/resources/icons8-star-50 (outline).png"
          alt=""
        />
        <p class="nav-text">likes</p>
      </div>
      <div class="nav-link" id="people-i-follow-link">
        <img
          class="nav-bar-icon"
          src="@/resources/groups_FILL0_wght400_GRAD0_opsz48.png"
          alt=""
        />
        <p class="nav-text">following</p>
      </div>
      <div class="nav-link" id="followers-link">
        <img
          class="nav-bar-icon"
          src="@/resources/groups_2_FILL0_wght400_GRAD0_opsz48.png"
          alt=""
        />
        <p class="nav-text">followers</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "side-bar",
  components: {},
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap");

.side-bar {
  display: flex;
  width: 100%;
  flex-direction: column;
  background-color: white;
  align-items: center;
  font-family: "Open Sans", sans-serif;
}

#nav-user {
  display: flex;
  flex-direction: row;
  margin-left: -40px;
}

.settings-icon {
  margin-top: 15px;
  margin-right: 10px;
  max-height: 30px;
  max-width: 30px;
  border-radius: 100%;
}

#profile-icon {
  margin-top: 45px;
  max-width: 150px;
  padding: 0px;
  box-shadow: 1px 1px 25px var(--primary-background-color);
  border-radius: 100%;
}

#user-stats {
  width: 100%;
  margin-left: -40px;
  padding: 0px;
}

ul {
  margin-top: 20px;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}

li {
  color: black;
  list-style: none !important;
  text-align: center;
  font-size: 15px;
  font-family: "Open Sans", sans-serif;
  text-transform: uppercase;
}

.nav-list {
  margin-right: 60px;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  flex-grow: 1;
}

.nav-link {
  padding-right: 10px;
  display: flex;
  align-items: center;
  font-size: 22px;
}
.nav-link:hover .nav-bar-icon {
  opacity: 1;
}

.nav-bar-icon {
  max-height: 20px;
  max-width: 20px;
  margin-right: 5px;
  opacity: 0.8;
}

.nav-text {
  display: inline-block;
  position: relative;
  color: black;
  padding-right: 10px;
}

.nav-text:after {
  content: "";
  position: absolute;
  width: 100%;
  transform: scaleX(0);
  height: 2px;
  bottom: 0;
  left: 0;
  background-color: black;
  transform-origin: bottom right;
  transition: transform 0.25s ease-out;
}

.nav-text:hover:after {
  transform: scaleX(1);
  transform-origin: bottom left;
}
</style>