<template>
  <div id="upload">
      <form id="upload-form" v-on:submit.prevent="uploadPost">
          <button id="image-input" v-on:click="uploadPhoto">Upload Image</button>
        
        <img id=image-preview v-bind:src='imageUrl'/>

          <input id="caption"
          type="text"
          placeholder="Enter caption"
          v-model="post.caption"/>

        <button type="submit">Post</button>
      </form>
      
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
                img: ""
            },
            imageUrl: "",
            preview: true
        }
        
    },
    methods: {
        uploadPost(){
            postService.addPost(this.post)
            this.imageUrl = ""
            this.caption = ""
        },

        uploadPhoto(){
             window.cloudinary
        .openUploadWidget(
          { cloud_name: "dcipg5scy", upload_preset: "TE-GRAM", maxFiles: 1 },
          (error, result) => {
            if (!error && result && result.event === "success") {
                console.log(result.info.url)
                this.post.img = result.info.url
                this.imageUrl = result.info.url

            } else {
                console.log(error)
            }}
        )
        .open();

        }

    
    },



   

}
</script>

<style>

#upload{
    text-align: center;
}

#image-input{
    display: block;
    margin: auto;
}

#caption{
    display: block;
    width: 20rem;
    margin: auto;
}

img{
    width: 400px;
}
</style>