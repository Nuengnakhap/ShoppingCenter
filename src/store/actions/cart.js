import { SET_CART } from "../actionTypes";

export const setCart = (data) => async dispatch => {
  dispatch({ type: SET_CART, payload: data });
}