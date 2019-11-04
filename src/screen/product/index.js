import React from "react";
import { Route, Switch, useRouteMatch } from "react-router-dom";
import DetailScreen from "./DetailScreen";
import ProductScreen from "./ProductScreen";


function ProductNavigator(props) {
  let match = useRouteMatch();

    return (
      <div className="content">
{/* 
        <ul>
          <li>
            <Link to={`${match.url}/components`}>Components</Link>
          </li>
          <li>
            <Link to={`${match.url}/props-v-state`}>Props v. State</Link>
          </li>
        </ul> */}
        <Switch>
          <Route path={`${match.path}/:productId`}>
            <DetailScreen />
          </Route>
          <Route path={match.path}>
            <ProductScreen {...props} url={match.url} />
          </Route>
        </Switch>
      </div>
    );
}

export default ProductNavigator

