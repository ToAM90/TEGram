<template>

  <div class="post-header-bar">
<<<<<<< HEAD
    <router-link
      class="display-name"
      v-bind:to="{ name: 'profile', params: { id: accountId } }"
    >
      {{ account.displayName }}
    </router-link>
=======
      <router-link class="display-name" v-bind:to="{name: 'profile', params: {id: accountId}}">
      this is displayName: {{account.displayName}}
      </router-link>
>>>>>>> 999ad3e59b4c8e9b7a2c67fa86e04759d5241e3b
  </div>
</template>

<script>
import AccountService from "../services/AccountService";
export default {
<<<<<<< HEAD
  name: "post-header",
  props: { accountId: Number },
  data() {
    return {
      account: {
        accountId: 0,
        displayName: "",
      },
    };
  },
  created() {
    AccountService.getAccountOther(this.accountId).then(
      (response) => (this.account = response.data)
    );
  },
};
=======
    name: "post-header",
    props: {accountId: Number},
    data(){
        return {
            account: {
                accountId: 0,
                displayName: ""
            }
          
        }
    },
    created(){
        AccountService.getAccountOther(this.accountId).then((response) => {
            this.account = response.data;
            console.log(response.data)
            console.log("the display name is: " + this.account.displayName)
            console.log("the account id is: " + this.account.accountId)
        } )
      
       
    }    
    }
>>>>>>> 999ad3e59b4c8e9b7a2c67fa86e04759d5241e3b
</script>

<style>
.post-header-bar {
  background: var(--panel-background-color);
  box-shadow: 0px 0px 5px rgb(172, 169, 169);
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  height: 20px;
  color: black;
  border-radius: 4px 4px 0px 0px;
}

.display-name {
  margin: auto;
}
</style>