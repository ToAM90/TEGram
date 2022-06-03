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

    <div v-if="creatingPost === true" id="upload">
      <button
        id="upload_widget"
        class="cloudinary-button"
        @click.prevent="uploadPhoto"
      >
        Upload Image
      </button>
      <form id="upload-form">
        <!-- <form id="upload-form" v-on:submit.prevent="uploadPost"> -->
        <textarea
          name=""
          id="upload-caption-input"
          cols="30"
          rows="4"
          placeholder="Enter caption"
          v-model="post.caption"
        ></textarea>
        <!-- <div id="privated">
          <input
            id="upload-checkbox-private"
            type="checkbox"
            v-model="post.privated"
          /> -->
        <!-- <label for="upload-checkbox-private">Private {{ privated }}</label> -->
        <!-- </div> -->
        <div id="form-submit-buttons">
          <button id="cancel-upload" @click.prevent="toggleCreatingPost">
            Cancel
          </button>
          <button id="submit-upload" @click="uploadPost" type="submit">
            Post
          </button>
        </div>
      </form>
    </div>
    <div class="nav-list">
      <div class="nav-link" id="make-a-post-link" v-if="creatingPost === false">
        <img
          class="nav-bar-icon"
          src="@/resources/add_photo_alternate_FILL0_wght400_GRAD0_opsz48.png"
          alt=""
        />
        <p @click="toggleCreatingPost" class="nav-text">create a post</p>
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
      <div class="nav-link" id="logout-link">
        <img
          class="nav-bar-icon"
          src="@/resources/logout_FILL0_wght400_GRAD0_opsz48 (1).png"
          alt=""
        />
        <p class="nav-text" @click="logout">logout</p>
      </div>
    </div>
  </div>
</template>

<script>
import postService from "../services/PostService";

export default {
  name: "side-bar",
  components: {},
  data() {
    return {
      post: {
        caption: "",
        img: "",
        privated: false,
      },
      imageUrl: "",
      preview: true,
      creatingPost: false,
    };
  },
  methods: {
    logout() {
      this.$store.commit("LOGOUT");
      this.$router.push("/login");
    },
    toggleCreatingPost() {
      this.creatingPost = !this.creatingPost;
    },
    uploadPost() {
      // this.$store.commit("ADD_POST", this.post);
      //if this post has no url >> cannot post
      postService.addPost(this.post).then((response) => {
        if (response.status == 201 || response.status == 200) {
          this.imageUrl = "";
          this.post.caption = "";
          this.post.img = "";
          this.privated = false;
        } else {
          console.log("placeholder event");
          //cannot make this post ^
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
  },
  computed: {
    getPosts() {
      return this.$store.state.posts;
    },
  },
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
  box-shadow: 0px 2px 10px rgb(184, 184, 184);
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
  padding-right: 0px;
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
  box-shadow: 0px 5px 3px var(--sign-up-blue);
}

.nav-text:hover:after {
  transform: scaleX(1);
  transform-origin: bottom left;
}

#upload {
  margin-top: 30px;
  width: 275px;
  display: flex;
  flex-direction: column;
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