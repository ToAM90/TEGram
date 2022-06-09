<template>
  <div id="comments">
    <div v-for="comment in comments" v-bind:key="comment.id" class="comment">
      <div id="indiv-comment">
        <router-link
          v-bind:to="{ name: 'profile', params: { id: comment.accountId } }"
        >
          <span class="comment-username">{{ comment.username }}: </span>
        </router-link>

        <div class="comment-with-icon">
          <span class="comment-text">{{ comment.commentText }}</span>
          <img
            id="delete-icon"
            v-on:click="deleteComment(comment.commentId)"
            v-if="comment.accountId == $store.state.currentAccount.accountId"
            src="../assets/icons8-delete-24.png"
            alt=""
          />
        </div>
      </div>
    </div>

    <form v-on:submit.prevent="addComment">
      <textarea
        id="comment-input"
        type="text"
        placeholder="Make A Comment"
        v-model="newComment.commentText"
      />


      <div class="comment-form-buttons">
        <button
          class="comment-form-button cancel-comment"
          v-on:click="newComment.commentText = ''">
          Cancel
        </button>
        <button class="comment-form-button send-comment" type="submit">
          Send
        </button>
      </div>
    </form>
  </div>
</template>

<script>
import CommentService from "../services/CommentService.js";
export default {
  name: "comment",
  data() {
    return {
      comments: [],
      newComment: {
        postId: -1,
        commentText: "",
      },
    };
  },
  created() {
    this.comments = this.$store.state.currentPost.comments;
  },
  methods: {
    addComment() {
      this.newComment.postId = this.$store.state.currentPost.postId;
      CommentService.addComment(this.newComment).then((response) =>{
        if(response.status == 201){
      //       this.newComment.accountId = this.$store.state.currentAccount.accountId;
      //       this.newComment.username = this.$store.state.currentAccount.displayName;
      // this.$store.commit("ADD_COMMENT", this.newComment)
      // this.newComment.commentText = ""
      this.$router.go(0);
        }
      })
    
    },
    deleteComment(commentId) {
      CommentService.removeComment(commentId).then((response) => {
        if(response.status === 204){
           this.$store.commit("REMOVE_COMMENT", commentId)
        }
      })
     
    },
  },
};
</script>

<style lang="css" scoped>
#comments {
  width: 100%;
  display: flex;
  flex-direction: column;

  font-family: "Montserrat", sans-serif;
  font-weight: 300;
}
#comment-input {
  margin: 4px;
  margin-bottom: 0px;
  min-width: calc(100% - 15px);
  max-width: calc(100% -15px);
}

.comment-form-button {
  width: 42.5%;
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
#indiv-comment:after {
  content: "";
  display: block;
  margin: 0 auto;
  width: 75%;
  padding-bottom: 5px;
  border-bottom: 2px rgb(35, 33, 37) solid;
}
/* #indiv-comment:before {
  content: "";
  display: block;
  margin: 0 auto;
  width: 50%;
  padding-top: 20px;
  border-top: 2px rgb(35, 33, 37) solid;
} */

.comment {
  margin: 10px;
  display: flex;
  width: calc(100% - 18px);
  flex-direction: column;
}
.comment-username {
  /* font-style: italic; */
  /* color: black; */
  text-decoration-line: underline;
  color: #ff48a3;

  line-height: 16px;
  padding-right: 10px;
}

.comment-text {
  color: black;
  width: 100%;
  word-break: break-word;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  line-height: 16px;
  max-height: 96px;
  -webkit-line-clamp: 6;
  -webkit-box-orient: vertical;
  padding: 10px;
}
#delete-icon {
  max-width: 17px;
  max-height: 17px;
}
.comment-with-icon {
  display: flex;
  flex-direction: row;
  align-items: flex-end;
}

#delete-icon{
  cursor: pointer;
}
</style>