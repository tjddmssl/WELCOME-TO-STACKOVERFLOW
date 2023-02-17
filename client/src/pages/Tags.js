import React from 'react'
import Tag from '../components/Tag'
import Header from '../components/Header'
import Sidebar from '../components/Sidebar'

function Tags() {
  return (
    <div>
      <h1>Tags Page</h1>
      <Header />
      <Sidebar />
      Tags <br />
      A tag is a keyword or label ~~~ <br />
      <input type="text" placeholder='Filter by tag name'></input>
      <Tag />
    </div>
  )
}

export default Tags
