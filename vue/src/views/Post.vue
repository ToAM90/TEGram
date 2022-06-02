<template>
  <div id="post">
      {{post}}
  </div>
</template>

<script>
import postService from "../services/PostService";

export default {
    name: "post",
    data() {
        return {
            postId: -1,
            post: {
                postId: 0,
                accountId: 0,
                img: "",
                caption: "",
                postDate: "",
                privated: false,
                comments: [],
                liked: false,
                favorited: false,
                likesCount: 0
            }
        }
    },
    created(){
        this.postId = this.$route.params.id;
        postService.getPost(this.postId).then((response) => {
            this.post = response.data;
        })
    }

}
</script>

<style>

</style>