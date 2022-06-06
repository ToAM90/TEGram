<template>
  <div id="upload">
    <button
      id="upload_widget"
      class="cloudinary-button"
      @click.prevent="uploadPhoto"
    >
      Upload Image
    </button>

    <form id="upload-form">
      <div class="uploaded-photo-diplay">
        <p v-if="!imageUrl.includes('$$$$$$')">Successfully Uploaded</p>
      </div>
      <textarea
        name=""
        id="upload-caption-input"
        cols="30"
        rows="4"
        placeholder="Enter caption"
        v-model="createPost.caption"
      ></textarea>
       <div id="privated">
        <input id="checkbox" type="checkbox" v-model="post.privated" />
        <label for="checkbox">Private {{ privated }}</label> <br />
      </div>
      <div id="form-submit-buttons">
        <button id="cancel-upload" @click.prevent="toggleCreatingPost">
          Cancel
        </button>
        <button id="submit-upload" @click.prevent="uploadPost" type="submit">
          Post
        </button>
      </div>
    </form> 
  </div>
</template>

<script>
import postService from "@/services/PostService.js";

export default {
  name: "create-post",
  data() {
    return {
      createPost: {
        accountId: "",
        caption: " ",
        img: "",
        privated: false,
      },

      imageUrl: "$$$$$$",
      preview: true,
      creatingPost: false,
      privated: false,
    };
  },
  methods: {
    toggleCreatingPost() {
      this.creatingPost = !this.creatingPost;
    },

    createTempPost(post) {
      let today = new Date();
      let dd = String(today.getDate()).padStart(2, "0");
      let mm = String(today.getMonth() + 1).padStart(2, "0"); //January is 0!
      let hours = String(today.getHours()).padStart(2, "0");
      let minutes = String(today.getMinutes()).padStart(2, "0");
      let seconds = String(today.getSeconds()).padStart(2, "0");
      let milliseconds = String(today.getMilliseconds()).padStart(6, "0");
      let yyyy = today.getFullYear();

      let currentDate =
        yyyy +
        "-" +
        mm +
        "-" +
        dd +
        "t" +
        hours +
        ":" +
        minutes +
        ":" +
        seconds +
        "." +
        milliseconds;
      console.log(this.$store.state.currentAccount);
      return {
        accountId: this.$store.state.currentAccount.accountId,
        caption: post.caption,
        comments: [],
        img: post.img,
        likesCount: 0,
        postDate: currentDate,
        postId: this.$store.state.posts.length + 1,
        privated: "false",
        liked: false,
      };
    },

    uploadPost() {
      postService.addPost(this.createPost).then((response) => {
        if (response.status == 201 || response.status == 200) {
          this.$store.commit("ADD_POST", this.createTempPost(this.createPost));
          this.imageUrl = "";
          this.createPost.caption = " ";
          this.createPost.img = "";
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
              this.createPost.img = result.info.url;
              this.imageUrl = result.info.url;
            } else {
              console.log(error);
            }
          }
        )
        .open();
    },
  },
};
</script>

<style lang="css" scoped>
#upload {
  width: calc(100% - 20px);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-color: white;
  border: 5px rgba(0, 0, 0, 0.308) solid;
  padding: 5px;

  border-radius: 10px;
}

.uploaded-photo-diplay {
  padding-top: 10px;

  padding-bottom: 10px;
  color: green;
}

#upload-widget {
  width: 200px !important;
}

.cloudinary-button {
  width: 100% !important;
  padding: 0px !important;
  height: 30px !important;
}

#upload-form {
  width: 100%;
}
#form-submit-buttons {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  width: 100%;
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

  margin-top: 5px;

  margin-bottom: 5px;
  width: calc(100% - 10px);

  height: 100% !important;
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