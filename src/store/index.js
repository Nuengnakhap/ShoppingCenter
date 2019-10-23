import { createStore, combineReducers, applyMiddleware } from "redux";
import rootReducer from "./reducers";
import ReduxActionsPromise from "redux-promise";
import thunk from "redux-thunk";

export default createStore(
  combineReducers(rootReducer),
  applyMiddleware(thunk, ReduxActionsPromise)
);
