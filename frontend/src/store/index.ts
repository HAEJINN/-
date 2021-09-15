// Vuex 조립
// import state from "./state";
// import * as getters from "./getters";
// import * as mutations from "./mutations";
import * as userActions from "./actions/userActions";

const root = {
  namespaced: true,
  // state,
  // getters,
  // mutations,
  userActions,
};

export default root;
