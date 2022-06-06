<template>
  <div id=comments>
      <div v-for="comment in commentList" v-bind:key="comment.commentId">
        <p>{{comment.username}}</p>
        <p>{{comment.commentText}}</p>
        <i id="delete-comment-btn" v-on:click="comment.commentId" v-if="comment.accountId == this.$store.currentAccount.accountId">
      </div>

      <form v-on:submit.prevent="addComment">
        <textarea
        id="comment"
        type="text"
        placeholder="Make A Comment :)"
        v-model="comment" />
        <button type="submit">Submit</button>
      </form>
      <button v-on:click="comment = '' ">Cancel</button>
  </div>
</template>

<script>
import CommentService from "../services/CommentService.js";
export default {
    data(){
        return {
            comment: ""
        }
    },
    computed:{
        commentList() {
            return this.$store.state.currentPost.comments;
        }
    },
    methods: {
        addComment(){
            CommentService.addComment(this.comment);
        },
        deleteComment(commentId){
            CommentService.removeComment(commentId);
        }
     
    }

}
</script>

<style>

</style>