import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    posts: [],
    followAccounts: [],
    account: {},
    accounts: [],
    currentAccount: {},
    currentView: '',
    currentPost: {}
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    ADD_POST(state, post) {
      state.posts.unshift(post)
    },
    INITIALIZE_POSTS(state, posts) {
      state.posts = posts
    },
    TOGGLE_LIKE(state, postId) {
      state.posts.forEach(post => {
        if (post.postId == postId) {
          post.liked = !post.liked
          if (post.liked) {
            post.likesCount++
          } else {
            post.likesCount--
          }
        }
      })
    },
    TOGGLE_FAVORITE(state, postId) {
      state.posts.forEach(post => {
        if (post.postId == postId) {
          post.favorited = !post.favorited
        }
      })
    }, 
    TOGGLE_FOLLOW(state, accountId) {
      state.accounts.forEach(account => {
        if (account.accountId == accountId) {
            account.followed = !account.followed
            if(account.followed){
              state.currentAccount.numFollowing++
            } else {
              state.currentAccount.numFollowing--
            }
      }})
    },
    TOGGLE_FOLLOW_FOLLOW(state, accountId) {
      state.followAccounts.forEach(account => {
        if (account.accountId == accountId) {
            account.followed = !account.followed
            if(account.followed){
              state.currentAccount.numFollowing++
            } else {
              state.currentAccount.numFollowing--
            }
      }})
    },
    SET_FOLLOW_ACCOUNTS(state, accounts){
      state.followAccounts = accounts
    },
    SET_ACCOUNT(state, newAccount) {
      state.account = newAccount;
    },
    SET_CURRENT_ACCOUNT(state, newCurrentAccount) {
      state.currentAccount = newCurrentAccount;
    },
    CHANGE_CURRENT_VIEW(state, view) {
      state.currentView = view
    },
    SET_CURRENT_POST(state, post) {
      state.currentPost = post
    },
    CLEAR_ACCOUNTS(state) {
      state.accounts = []
    },
    ADD_ACCOUNT(state, newAccount) {
      state.accounts.push(newAccount)
    },
    ADD_COMMENT(state, comment){
      state.currentPost.comments.push(comment)
    },
    REMOVE_COMMENT(state, id){
      state.currentPost.comments.splice(
        (state.currentPost.comments.findIndex((comment) => comment.commentId === id)), 1
      )
    },
    REMOVE_POST(state, id){
      state.posts.splice(
        (state.posts.findIndex((post) => post.postId === id)), 1
      )
    }
  }
})
