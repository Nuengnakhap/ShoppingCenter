import React, { Component } from 'react'
import "../../assets/css/global.css"
import "../../assets/css/store.css"
import Cart from '../../components/cart'

export default class ProductScreen extends Component {
  render() {
    return (
      <div className="content">
        <Cart cart={[]} />
        
      </div>
    )
  }
}
