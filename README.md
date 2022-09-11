# 원티드 프리온보딩 사전과제

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

![Untitled](%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%90%E1%85%B5%E1%84%83%E1%85%B3%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%84%8B%E1%85%A9%E1%86%AB%E1%84%87%E1%85%A9%E1%84%83%E1%85%B5%E1%86%BC%20%E1%84%89%E1%85%A1%E1%84%8C%E1%85%A5%E1%86%AB%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%204cdb0649b4eb4c19bd0b22e6f5570bf6/Untitled.png)

# 📱 Result

![Untitled](%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%90%E1%85%B5%E1%84%83%E1%85%B3%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%84%8B%E1%85%A9%E1%86%AB%E1%84%87%E1%85%A9%E1%84%83%E1%85%B5%E1%86%BC%20%E1%84%89%E1%85%A1%E1%84%8C%E1%85%A5%E1%86%AB%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%204cdb0649b4eb4c19bd0b22e6f5570bf6/Untitled%201.png)

![Untitled](%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%90%E1%85%B5%E1%84%83%E1%85%B3%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%84%8B%E1%85%A9%E1%86%AB%E1%84%87%E1%85%A9%E1%84%83%E1%85%B5%E1%86%BC%20%E1%84%89%E1%85%A1%E1%84%8C%E1%85%A5%E1%86%AB%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%204cdb0649b4eb4c19bd0b22e6f5570bf6/Untitled%202.png)

![Untitled](%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%90%E1%85%B5%E1%84%83%E1%85%B3%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%84%8B%E1%85%A9%E1%86%AB%E1%84%87%E1%85%A9%E1%84%83%E1%85%B5%E1%86%BC%20%E1%84%89%E1%85%A1%E1%84%8C%E1%85%A5%E1%86%AB%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%204cdb0649b4eb4c19bd0b22e6f5570bf6/Untitled%203.png)

![Untitled](%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%90%E1%85%B5%E1%84%83%E1%85%B3%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%84%8B%E1%85%A9%E1%86%AB%E1%84%87%E1%85%A9%E1%84%83%E1%85%B5%E1%86%BC%20%E1%84%89%E1%85%A1%E1%84%8C%E1%85%A5%E1%86%AB%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%204cdb0649b4eb4c19bd0b22e6f5570bf6/Untitled%204.png)

![Untitled](%E1%84%8B%E1%85%AF%E1%86%AB%E1%84%90%E1%85%B5%E1%84%83%E1%85%B3%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%84%8B%E1%85%A9%E1%86%AB%E1%84%87%E1%85%A9%E1%84%83%E1%85%B5%E1%86%BC%20%E1%84%89%E1%85%A1%E1%84%8C%E1%85%A5%E1%86%AB%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%204cdb0649b4eb4c19bd0b22e6f5570bf6/Untitled%205.png)
