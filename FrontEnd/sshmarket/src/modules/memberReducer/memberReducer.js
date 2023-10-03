const INITIAL_DATA = {
  id: "",
  nickname: "",
  profile: "",
};

export function MemberReducer(state = INITIAL_DATA, action) {
  switch (action.type) {
    case "LOGIN":
      return { ...action.data };
    case "LOGOUT":
      return { ...INITIAL_DATA };
    case "CHANGEPROFILE":
      return {
        ...state,
        profile: action.profile,
      };
    case "CHANGENICKNAME":
      return {
        ...state,
        nickname: action.nickname,
      };

    default:
      return state;
  }
}
