# 원티드 프리온보딩 사전과제

# Team(학연지연우연인연)

# 📱Introduction

- [newsapi](https://newsapi.org/)의 top-headlines API를 활용하여 뉴스 목록을 나타냅니다.
- 뉴스 클릭시 상세 내용을 보여줍니다.
- 저장 버튼을 통해 원하는 뉴스를 저장할 수 있습니다.

# 🔧 Tech

- MVVM + Celan Architecture
- Navigation
- Room
- Retrofit2 + OkHttp3
- Coroutine, Flow, Hilt, Paging

# 🗂️ Structure


~~~
├─base
│  └─di
├─data
│  ├─database
│  ├─dto
│  ├─paging
│  ├─repository
│  │  ├─local
│  │  └─remote
│  └─service
├─di
├─domain
│  ├─mapper
│  ├─model
│  └─usecase
├─presentation
│  └─feature
│      ├─adapter
│      ├─category
│      ├─detail
│      ├─saved
│      ├─topnews
│      └─viewholder
└─utils
~~~

- base
    - BaseActivity 와 BaseFragment 작성
    - di
        - Retrofit 모듈 - Retrofit과 OkHttep를 proivde
        - Databse모듈 - Database와 Dao를 provide
- data
    - RoomDatabase 빌더 및 Dao
    - Api response 데이터 클래스
    - paging - 서비스를 주입받아 position 증가시 재 호출하여 [LoadResult.Page](http://LoadResult.Page) 객체 return
    - repository
        - local - Dao 를 주입받아 DB안의 뉴스 저장, 가져오기 삭제, 검사 쿼리결과 return
        - PagingSource를 통해 얻은 Pager 객체를 flow로 변환하여 return
    - news api GET
- di
    - RemoteNewsRepository와 LocalNewsRepository를 각각 Impl과 bind
    - NewsService를 retrofit 객체로 생성하여 provide
- domain
    - API 호출 결과를 mapping함
    - UseCase로 확인, 삭제, 가져오기, 저장 불러오기, 저장하기 등의 기능을 분리함
- presentation
    - 바인딩 어댑터와 리스트 어댑터 PagingData어댑터가 있습니다.
    - 각각의 화면의 뷰 작성과 공통 뷰모델로 데이터를 공유하고 처리합니다.
- utils
    - Header의 인터셉터 작성

# 📟 Navigation Graph

![navgragh](https://user-images.githubusercontent.com/86879099/189529446-1745318f-5c62-443f-882d-d6980190d61a.png)

# 📱 Result
![topnews](https://user-images.githubusercontent.com/86879099/189529491-e09cc0ac-e278-467d-b0e3-be1126d6e42d.png)![cat](https://user-images.githubusercontent.com/86879099/189529497-d9603ab7-3f52-4d68-9fde-da9296dcbe7a.png)![sport](https://user-images.githubusercontent.com/86879099/189529494-b38d24a6-051e-4340-95ea-c0340a42dfe9.png)![detail](https://user-images.githubusercontent.com/86879099/189529503-3588d99f-4ac4-4ed6-b543-2f486bc74735.png)![saved](https://user-images.githubusercontent.com/86879099/189529504-434ecfa3-7beb-42ac-8870-9950bc658e48.png)
