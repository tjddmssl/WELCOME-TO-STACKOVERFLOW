import React from 'react'
import QButton from '../components/QButton'
import QLists from '../components/QLists'
import Header from '../components/Header'
import Sidebar from '../components/Sidebar'

function Search() {
  return (
    <div>
      <h1>Search page</h1>
      <Header />
      <Sidebar />
      Search Results  <QButton />
      <QLists />
    </div>
  )
}

export default Search
