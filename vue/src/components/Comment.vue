<template>
  <div id="comments">
    <div v-for="comment in commentList" v-bind:key="comment.commentId">
      <p>{{ comment.username }}</p>
      <p>{{ comment.commentText }}</p>
      <i
        id="delete-comment-btn"
        v-on:click="comment.commentId"
        v-if="comment.accountId == this.$store.currentAccount.accountId"
      />
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
      <button
        class="comment-form-button cancel-comment"
        v-on:click="comment = ''"
      >
        Cancel
      </button>
      <button class="comment-form-button send-comment" @click="addComment">
        Send
      </button>
    </div>
  </div>
</template>

<script>
import CommentService from "../services/CommentService.js";
export default {
  name: "comment",
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
  margin: 4px;
  margin-bottom: 0px;
  min-width: calc(100% - 15px);
  max-width: calc(100% -15px);
}

.comment-form-button {
  width: 45%;
  height: 25px;
  margin: 8px;
}

.comment-form-buttons {
  display: flex;
  flex-direction: row;

  justify-content: space-between;
  width: 100%;
  margin-right: 0px;

  margin-left: 0px;
}

.cancel-comment {
  background-color: rgb(212, 87, 87) !important;
  box-shadow: 0px 0px 5px rgb(177, 47, 47) !important;
  border-radius: 5px;
  color: white;
}

.send-comment {
  background-color: rgba(168, 49, 197, 0.555) !important;
  box-shadow: 0px 0px 5px rgba(136, 47, 177, 0.568) !important;
  border-radius: 5px;
  color: white;
}
</style>