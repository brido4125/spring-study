import logo from "./logo.svg";
import "./App.css";
import Todo from "./Todo";
import React from "react";
import { render } from "@testing-library/react";

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      item: { id: 0, title: "Work out", done: true },
    };
  }
  render() {
    return (
      <div className="App">
        <Todo item={this.state.item} />
      </div>
    );
  }
}

export default App;
