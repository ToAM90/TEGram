<template>
  <div class="profile-header">
    <div class="nav-list">
      <div class="nav-link" id="home-link" @click="routeToHome">
        <img
          class="nav-bar-icon"
          src="@/assets/home_FILL0_wght400_GRAD0_opsz48.png"
          alt=""
        />
        <p class="nav-text">home</p>
      </div>

      <div class="nav-link" id="favorited-link" v-if="windowWidth > 700">
        <img class="nav-bar-icon" src="@/assets/emptyBookmark.png" alt="" />
        <router-link v-bind:to="{ name: 'favorites' }">
          <p class="nav-text">favorited</p>
        </router-link>
      </div>

      <div class="nav-link" id="likes-link" v-if="windowWidth > 1400">
        <img class="nav-bar-icon" src="@/assets/icons8-heart-24.png" alt="" />
        <router-link v-bind:to="{ name: 'liked' }">
        <p class="nav-text">likes</p>
        </router-link>
      </div>

      <div id="nav-user" v-if="!isScrolled" @click="uploadPFP">
        <img :src="getProfilePic" alt="" id="profile-icon" />
      </div>

      <div class="nav-link" id="people-i-follow-link" v-if="windowWidth > 700">
        <img
          class="nav-bar-icon"
          src="@/assets/groups_FILL0_wght400_GRAD0_opsz48.png"
          alt=""
        />
        <p class="nav-text" @click="routeToFollowing">following</p>
      </div>
      <div class="nav-link" id="followers-link" v-if="windowWidth > 1400">
        <img
          class="nav-bar-icon"
          src="@/assets/groups_2_FILL0_wght400_GRAD0_opsz48.png"
          alt=""
        />
        <p class="nav-text" @click="routeToFollowers">followers</p>
      </div>
      <div class="nav-link" id="logout-link">
        <img
          class="nav-bar-icon"
          src="@/assets/logout_FILL0_wght400_GRAD0_opsz48.png"
          alt=""
        />
        <p class="nav-text" @click="logout">logout</p>
      </div>
    </div>
  </div>
</template>

<script>
import postService from "../services/PostService";

import accountService from "../services/AccountService";

export default {
  name: "profile-header",
  components: {},
  data() {
    return {
      post: {
        caption: "",
        img: "",
        privated: false,
      },
      imageUrl: "No image currently selected",
      preview: true,
      creatingPost: false,
      windowWidth: 0,
      isScrolled: false,
    };
  },
  created() {
    window.addEventListener("resize", this.calcWindowWidth);
    this.windowWidth = window.innerWidth;
  },
  // mounted() {
  //   this.windowWidth = window.innerWidth;
  // },
  methods: {
    logout() {
      this.$store.commit("LOGOUT");
      this.$router.push("/login");
    },
    routeToHome() {
      this.$router.push("/");
    },
    routeToFollowing() {
      this.$store.commit("SET_FOLLOWVIEW", 1);
      this.$router.push("/following");
    },
    routeToFollowers() {
      this.$store.commit("SET_FOLLOWVIEW", 2);
      this.$router.push("/followers");
    },
    toggleCreatingPost() {
      this.creatingPost = !this.creatingPost;
    },
    uploadPost() {
      postService.addPost(this.post).then((response) => {
        if (response.status == 201 || response.status == 200) {
          this.imageUrl = "";
          this.post.caption = "";
          this.post.img = "";
          this.privated = false;
        } else {
          console.log("placeholder event");
        }
        this.creatingPost = !this.creatingPost;
      });
    },
    uploadPhoto() {
      window.cloudinary
        .openUploadWidget(
          {
            cloud_name: "dcipg5scy",
            upload_preset: "TE-GRAM",
            maxFiles: 1,
          },
          (error, result) => {
            if (!error && result && result.event === "success") {
              console.log(result.info.url);
              this.post.img = result.info.url;
              this.imageUrl = result.info.url;
            } else {
              console.log(error);
            }
          }
        )
        .open();
    },
    uploadPFP() {
      window.cloudinary
        .openUploadWidget(
          {
            cloud_name: "dcipg5scy",
            upload_preset: "TE-GRAM",
            maxFiles: 1,
          },
          (error, result) => {
            //add verify clause for correct user
            if (!error && result && result.event === "success") {
              console.log(result.info.url);

              accountService
                .updateAccount({
                  accountId: this.$store.state.currentAccount.accountId,
                  userId: this.$store.state.currentAccount.userId,
                  displayName: this.$store.state.currentAccount.displayName,
                  profileImg: result.info.url,
                })
                .then((response) => {
                  console.log(response.status);
                  this.$store.commit("SET_CURRENT_ACCOUNT", {
                    accountId: this.$store.state.currentAccount.accountId,
                    userId: this.$store.state.currentAccount.userId,
                    displayName: this.$store.state.currentAccount.displayName,
                    profileImg: result.info.url,
                  });
                });
            } else {
              console.log(error);
            }
          }
        )
        .open();
    },
    calcWindowWidth() {
      this.windowWidth = window.innerWidth;
    },
    calcIsScrolled() {
      console.log(Window.scrollY);
      return (this.isScrolled = window.scrollY !== 0);
    },
  },
  computed: {
    getPosts() {
      return this.$store.state.posts;
    },
    getProfilePic() {
      console.log(this.$store.state.account.profileImg);
      return this.$store.state.account.profileImg;
    },
  },
};
</script>

<style lang="css" scoped>
@import url("https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap");

.profile-header {
  display: flex;
  width: 100%;
  flex-direction: row;
  /* background-color: white; */
  /* background-color: #616161; */
  background: #d1d1d3;
  align-items: center;
  font-family: "Open Sans", sans-serif;
  box-shadow: 0px 2px 10px rgb(184, 184, 184);
  justify-content: space-between;
  height: 100px;
}
#nav-user {
  /* display: flex; */
  /* flex-direction: row; */
  /* margin-left: 40px; */
}

.settings-icon {
  margin-top: 15px;
  margin-right: 10px;
  max-height: 30px;
  max-width: 30px;
  border-radius: 100%;

  background: #d1d1d3;
  -webkit-background-clip: border-box;
}

#profile-icon {
  margin-top: 45px;
  max-width: 150px;
  padding: 0px;
  box-shadow: 1px 1px 25px var(--primary-background-color);
  border-radius: 80%;
  /* border: 30px white solid; */
}

#user-stats {
  width: 100%;
  /* margin-left: -40px; */
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
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  flex-grow: 1;
}

.nav-link {
  /* margin-left: 5vw; */
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 22px;
  width: 10vw;
}
.nav-link:hover .nav-bar-icon {
  opacity: 1;
}

.nav-bar-icon {
  max-height: 20px;
  max-width: 20px;
  margin-right: 5px;
  opacity: 0.8;

  padding: 3px;
  border-radius: 7px;
  /* opacity: 0.8;

  background-image: url("http://res.cloudinary.com/dcipg5scy/image/upload/v1654357142/TE_GRAM/zelphyniwd4mjons4g3o.png");
  -webkit-background-clip: border-box; */

  background: #d1d1d3;
  -webkit-background-clip: border-box;
}

.nav-text {
  display: inline-block;
  position: relative;
  color: black;
  padding-right: 0px;
  cursor: pointer;
  /* 
  background-image: url("http://res.cloudinary.com/dcipg5scy/image/upload/v1654357142/TE_GRAM/zelphyniwd4mjons4g3o.png");
  -webkit-text-fill-color: transparent;
  -webkit-background-clip: text; */

  color: rgb(35, 33, 37);
}

.nav-text:after {
  content: "";
  position: absolute;
  width: 100%;
  transform: scaleX(0);
  height: 2px;
  bottom: 0;
  left: 0;
  background-color: rgb(138, 138, 138);
  transform-origin: bottom right;
  transition: transform 0.25s ease-out;
  box-shadow: 0px 5px 3px #b8c9f1;
}

.nav-text:hover:after {
  transform: scaleX(1);
  transform-origin: bottom left;
}

#upload {
  margin-top: 30px;
  width: 275px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
}

#upload-form {
}

#upload-widget {
  width: 200px !important;
}

.cloudinary-button {
  width: 200px !important;
  padding: 0px !important;
  height: 30px !important;
}

#form-submit-buttons {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  width: 208px;
  margin-right: 0px;

  margin-left: 0px;
}

#form-submit-buttons button {
  width: 45%;
  height: 25px;
  margin-top: 4px;
}

#cancel-upload {
  background-color: rgb(212, 87, 87);
  box-shadow: 0px 0px 5px rgb(177, 47, 47);
  border-radius: 5px;
  color: white;
}

#submit-upload {
  background-color: rgba(168, 49, 197, 0.555);
  box-shadow: 0px 0px 5px rgba(136, 47, 177, 0.568);
  border-radius: 5px;
  color: white;
}

#upload-caption-input {
  display: block;

  padding: 5px;

  margin: 5px;
  width: 186px;

  height: 60px !important;
  font-family: "Open Sans", sans-serif;
}

#privated {
  display: block;
  color: black;
}

#image-preview {
  width: 400px;
}
</style>