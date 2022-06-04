<template>
  <div v-if="creatingPost === true" id="upload">
    <button
      id="upload_widget"
      class="cloudinary-button"
      @click.prevent="uploadPhoto"
    >
      Upload Image
    </button>
    <div class="uploaded-photo-diplay">
      {{ imageUrl }}
    </div>
    <form id="upload-form">
      <textarea
        name=""
        id="upload-caption-input"
        cols="30"
        rows="4"
        placeholder="Enter caption"
        v-model="post.caption"
      ></textarea>
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
</template>

<script>
import postService from "@/services/PostService.js";

export default {
  name: "create-post",
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
    };
  },
  methods: {
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
  },
};
</script>

<style lang="css" scoped>
#upload {
  margin-top: 30px;
  width: 275px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.uploaded-photo-diplay {
  color: black;
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