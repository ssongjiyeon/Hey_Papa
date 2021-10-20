
const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: 'login', component: () => import('pages/Login.vue') },
      { path: 'mission', component: () => import('pages/Mission.vue') },
      { path: 'diary', component: () => import('pages/Diary.vue') },
      { path: 'home', component: () => import('pages/Home.vue') },
      { path: 'info', component: () => import('pages/Info.vue') },
      { path: 'my', component: () => import('pages/My.vue') },
    ]
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/Error404.vue')
  }
]

export default routes
