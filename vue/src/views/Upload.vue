<template>
  <div id="upload">
    <button
      id="upload_widget"
      class="cloudinary-button"
      v-on:click="uploadPhoto"
    >
      Upload Image
    </button>
    <form id="upload-form" v-on:submit.prevent="uploadPost">
      
      <img id="image-preview" v-bind:src="imageUrl" />

      <input
        id="caption"
        type="text"
        placeholder="Enter caption"
        v-model="post.caption"
      />
      <!-- <div id="privated">
        <input id="checkbox" type="checkbox" v-model="post.privated" />
        <label for="checkbox">Private {{ privated }}</label> <br />
      </div> -->
      <button id="submit" type="submit">Post</button>
    </form>
    <button v-on:click="deletePhoto">Cancel</button>
  </div>
</template>

<script>
import postService from "../services/PostService";

export default {
  name: "upload",
  data() {
    return {
      post: {
        caption: "",
        img: "",
        privated: false,
      },
      imageUrl: "",
      preview: true,
    };
  },
  methods: {
    uploadPost() {
      postService.addPost(this.post);
      this.imageUrl = "";
      this.post.caption = "";
      this.post.img = "";
      this.privated = false;
    },

    uploadPhoto() {
      window.cloudinary
        .openUploadWidget(
          { cloud_name: "dcipg5scy", upload_preset: "TE-GRAM", maxFiles: 1, 
          showCompletedButton: true,},
          (error, result) => {
            if (!error && result && result.event === "success") {
              // console.log(result);
              this.post.img = result.info.public_id;
              // console.log(this.post.img);
              // this.viewPhoto();
              
            } else {
              // console.log(error);
            }
          }
        )
        .open();
    },
    viewPhoto(){
      this.imageUrl = window.cloudinary.url(this.post.img)
    },
    deletePhoto(){
      window.cloudinary.v2.uploader.destroy(this.post.img)
    }
  },
};
</script>

<style>
#upload {
  text-align: center;
}

#upload-widget {
  display: block;
  margin: auto;
}

#caption {
  display: block;
  width: 20rem;
  margin: auto;
}

#privated {
  display: block;
}

#image-preview {
  width: 400px;
}
</style>