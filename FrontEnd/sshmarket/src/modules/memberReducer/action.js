export const LOGIN = "LOGIN";
export const LOGOUT = "LOGOUT";
export const CHANGEPROFILE = "CHANGEPROFILE";
export const CHANGENICKNAME = "CHANGENICKNAME";

export function Login(data) {
  return {
    type: LOGIN,
    data,
  };
}

export function Logout() {
  return {
    type: LOGOUT,
  };
}

export function ChangeProfile(profile) {
  return {
    type: CHANGEPROFILE,
    profile,
  };
}

export function ChangeNickname(nickname) {
  return {
    type: CHANGENICKNAME,
    nickname,
  };
}
