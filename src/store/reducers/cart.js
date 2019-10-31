import { SET_CART } from "../actionTypes";

const initState = {
  cart: []
};

export const cart = (state = initState, action) => {
  let newState = state;
  switch (action.type) {
    case SET_CART:
      newState.cart.push(action.payload);
      return newState;
    default:
      return newState;
  }
};
