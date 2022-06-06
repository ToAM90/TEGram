<template>
  <div id=comments>
      <div v-for="comment in commentList" v-bind:key="comment.commentId">
        <p>{{comment.username}}</p>
        <p>{{comment.commentText}}</p>
        <i id="delete-comment-btn" v-on:click="comment.commentId" v-if="comment.accountId == this.$store.currentAccount.accountId">
      </div>

    <form v-on:submit.prevent="addComment">
      <textarea
        id="comment-input"
        type="text"
        placeholder="Make A Comment :"
        v-model="comment"
      />
    </form>
    <div class="comment-form-buttons">
      <button class="comment-form-button" v-on:click="comment = ''">
        Cancel
      </button>
      <button class="comment-form-button" @click="addComment">Submit</button>
    </div>
  </div>
</template>

<script>
import CommentService from "../services/CommentService.js";
export default {
  data() {
    return {
      comment: "",
    };
  },
  computed: {
    commentList() {
      return this.$store.state.currentPost.comments;
    },
  },
  methods: {
    addComment() {
      CommentService.addComment(this.comment);
    },
    deleteComment(commentId) {
      CommentService.removeComment(commentId);
    },
  },
};
</script>

<style lang="css" scoped>
#comments {
  width: 100%;
  display: flex;
  flex-direction: column;
}
#comment-input {
  margin: 5px;
  margin-bottom: 0px;
  min-width: calc(100% - 15px);
  max-width: calc(100% -15px);
}

.comment-form-button {
  width: 45%;
  height: 25px;
  margin: 4px;
}

.comment-form-buttons {
  display: flex;
  flex-direction: row;

  justify-content: space-between;
  width: 100%;
  margin-right: 0px;

  margin-left: 0px;
}
</style>