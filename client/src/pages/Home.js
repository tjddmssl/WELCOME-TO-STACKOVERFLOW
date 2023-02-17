import React from 'react'
import Header from '../components/Header'
import QButton from '../components/QButton'
import QLists from '../components/QLists'
import Sidebar from '../components/Sidebar'

function Home() {
  return (
    <div>
      <h1>Home page</h1>
      <Header />
      <Sidebar />
      <QButton />
      <QLists />
    </div>
  )
}

export default Home
