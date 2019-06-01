const loginInput = {

  handleChange(event, value) {
    let newState = this.state
    let target = event.target.name
    newState[target] = value
    this.setState(newState)
  }

}

export default loginInput