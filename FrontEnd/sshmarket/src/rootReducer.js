import { persistReducer } from "redux-persist";
import { combineReducers } from "redux";
import storage from "redux-persist/lib/storage";
import { MemberReducer } from "./modules/memberReducer/memberReducer";

const persistConfig = {
  key: "root",
  storage,
  whitelist: ["MemberReducer"],
};

const rootReducer = combineReducers({
  MemberReducer,
})

export default persistReducer(persistConfig, rootReducer)