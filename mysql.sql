create table sailtotmdb.CarrierInfo
(
    carrier_id     int         not null,
    transport_id   int         not null,
    carrier_number varchar(50) null,
    send_date      datetime    null,
    receive_date   datetime    null,
    primary key (carrier_id, transport_id)
)
    engine = InnoDB;

create index fk_CarrierInfo_TransportTypes1_idx
    on sailtotmdb.CarrierInfo (transport_id);

create table sailtotmdb.CarriersPriceHistory
(
    history_id   int      not null
        primary key,
    transport_id int      not null,
    price        float    not null,
    date         datetime not null
)
    engine = InnoDB;

create table sailtotmdb.CartItems
(
    cart_id         int auto_increment,
    user_id         int           not null,
    quantity        int           not null,
    created_date    datetime      not null,
    num_iid         varchar(100)  not null,
    properties      varchar(3000) null,
    properties_name varchar(3000) null,
    primary key (cart_id, user_id)
)
    engine = InnoDB;

create index fk_Cart_list_Users_idx
    on sailtotmdb.CartItems (user_id);

create index num_iid
    on sailtotmdb.CartItems (num_iid);

create table sailtotmdb.ChinaDeliverDetails
(
    deliver_id   int auto_increment
        primary key,
    name         varchar(50)  not null,
    phone_number varchar(15)  not null,
    address      varchar(250) not null,
    status       int          not null
)
    engine = InnoDB;

create table sailtotmdb.ChinaDetails
(
    china_detail_id int           not null,
    deliver_id      int           not null,
    stuff_id        int           not null,
    taobao_order_id varchar(50)   null,
    send_date       datetime      null,
    receive_date    datetime      null,
    purchase_date   datetime      null,
    pay_sum         float         null,
    taobao_account  varchar(1000) null,
    primary key (china_detail_id, deliver_id, stuff_id)
)
    engine = InnoDB;

create index fk_ChinaDetails_ChinaDeliverDetails1_idx
    on sailtotmdb.ChinaDetails (deliver_id);

create index fk_ChinaDetails_Stuff1_idx
    on sailtotmdb.ChinaDetails (stuff_id);

create table sailtotmdb.EmailVerifiCode
(
    user_id      int      not null,
    created_date datetime null,
    dead_date    datetime null,
    code         int      not null
        primary key
)
    engine = InnoDB;

create table sailtotmdb.ExchangeRate
(
    rate_id      int auto_increment
        primary key,
    rmb          float    not null,
    usd          float    not null,
    manat        float    not null,
    updated_date datetime not null
)
    engine = InnoDB;

create table sailtotmdb.FavouriteItems
(
    favourite_id int auto_increment,
    user_id      int          not null,
    created_date datetime     not null,
    num_iid      varchar(100) not null,
    primary key (favourite_id, user_id)
)
    engine = InnoDB;

create index fk_Favourite_list_Users1_idx
    on sailtotmdb.FavouriteItems (user_id);

create index num_iid
    on sailtotmdb.FavouriteItems (num_iid);

create table sailtotmdb.GoodsDetails
(
    good_detail_id  int auto_increment
        primary key,
    quantity        int          not null,
    num_iid         varchar(100) not null,
    properties      text         null,
    properties_name text         null
)
    engine = InnoDB;

create index fk_Goods_details
    on sailtotmdb.GoodsDetails (num_iid);

create table sailtotmdb.HistoryItems
(
    history_id   int auto_increment,
    user_id      int          not null,
    num_iid      varchar(100) not null,
    created_date datetime     not null,
    primary key (history_id, user_id)
)
    engine = InnoDB;

create index HistoryItems_num_iid_index
    on sailtotmdb.HistoryItems (num_iid);

create index HistoryItems_user_id_index
    on sailtotmdb.HistoryItems (user_id);

create index fk_History_list_Users1_idx
    on sailtotmdb.HistoryItems (user_id);

create index num_iid
    on sailtotmdb.HistoryItems (num_iid);

create table sailtotmdb.InterestRate
(
    interest_id   int   not null
        primary key,
    interest_rate float not null
)
    engine = InnoDB;

create table sailtotmdb.MembershipBalanceHistoryType
(
    type_id int         not null
        primary key,
    type    varchar(50) not null
)
    engine = InnoDB;

create table sailtotmdb.MembershipUsers
(
    membership_id int auto_increment,
    user_id       int          not null,
    balance       float        not null,
    password      varchar(250) not null,
    created_date  datetime     not null,
    primary key (membership_id, user_id)
)
    engine = InnoDB;

create index fk_MembershipUsers_Users1_idx
    on sailtotmdb.MembershipUsers (user_id);

create table sailtotmdb.MembershipUsersBalanceHistory
(
    history_id    int auto_increment,
    membership_id int           not null,
    user_id       int           not null,
    stuff_id      int default 0 not null,
    status_id     int           not null,
    `change`      float         not null,
    created_date  datetime      not null,
    primary key (history_id, membership_id, user_id, stuff_id, status_id)
)
    engine = InnoDB;

create index fk_MembershipUsersBalanceHistory_MembershipUsers1_idx
    on sailtotmdb.MembershipUsersBalanceHistory (membership_id, user_id);

create index fk_Membership_users_history_Membership_history_status1_idx
    on sailtotmdb.MembershipUsersBalanceHistory (status_id);

create index fk_Membership_users_history_Stuff1_idx
    on sailtotmdb.MembershipUsersBalanceHistory (stuff_id);

create table sailtotmdb.OneBoundApiTaobaoProduct
(
    created_date    datetime      not null,
    detail_url      varchar(2000) null,
    num_iid         varchar(100)  not null
        primary key,
    pic_url         varchar(2000) not null,
    price           float         not null,
    promotion_price float         not null,
    sales           int           null,
    seller_nick     varchar(100)  null,
    title           varchar(1000) not null,
    q               varchar(200)  not null,
    status          int default 1 not null,
    title_zh        varchar(1000) null,
    seller_nick_zh  varchar(100)  null
)
    engine = InnoDB;

create table sailtotmdb.OrderStatusChangeHIstory
(
    order_status_change_history_id int auto_increment
        primary key,
    created_date                   datetime null,
    order_id                       int      null,
    stuff_id                       int      null,
    old_status                     int      null,
    new_status                     int      null
)
    engine = InnoDB;

create index OrderStatusChangeHIstory_OrdersType_type_id_fk
    on sailtotmdb.OrderStatusChangeHIstory (old_status);

create index OrderStatusChangeHIstory_OrdersType_type_id_fk2
    on sailtotmdb.OrderStatusChangeHIstory (new_status);

create index OrderStatusChangeHIstory_Orders_order_id_fk
    on sailtotmdb.OrderStatusChangeHIstory (order_id);

create index OrderStatusChangeHIstory_Stuff_stuff_id_fk
    on sailtotmdb.OrderStatusChangeHIstory (stuff_id);

create table sailtotmdb.Orders
(
    order_id            int auto_increment
        primary key,
    user_id             int           null,
    stuff_id            int default 0 null,
    good_detail_id      int           null,
    transport_detail_id int           null,
    china_detail_id     int           null,
    status_id           int           null,
    pickup_code         int           null,
    created_date        datetime      null,
    pay_sum_id          int           null,
    pay_expect          float         null,
    updated_date        datetime      null
)
    engine = InnoDB;

create index Orders_PayHistory_pay_id_fk2
    on sailtotmdb.Orders (pay_sum_id);

create index fk_Orders_ChinaDetails1_idx
    on sailtotmdb.Orders (china_detail_id);

create index fk_Orders_GoodsDetails1_idx
    on sailtotmdb.Orders (good_detail_id);

create index fk_Orders_OrdersType1_idx
    on sailtotmdb.Orders (status_id);

create table sailtotmdb.OrdersType
(
    type_id int         not null
        primary key,
    type    varchar(50) not null
)
    engine = InnoDB;

create table sailtotmdb.PayHistory
(
    pay_id        int auto_increment
        primary key,
    created_date  datetime         not null,
    pay_amount    float            not null,
    pay_type      int              not null,
    stuff_id      int default 1000 not null,
    pay_sum_id    int              null,
    pay_out_or_in int              null
)
    engine = InnoDB;

create index PayHistory_PaySum_pay_sum_id_fk
    on sailtotmdb.PayHistory (pay_sum_id);

create index PayHistory_PayType_pay_type_id_fk
    on sailtotmdb.PayHistory (pay_type);

create index PayHistory_Stuff_stuff_id_fk
    on sailtotmdb.PayHistory (stuff_id);

create table sailtotmdb.PaySum
(
    pay_sum_id   int auto_increment
        primary key,
    pay_now      float    null,
    pay_expect   float    null,
    created_date datetime null,
    updated_date datetime null
)
    engine = InnoDB;

create table sailtotmdb.PayType
(
    pay_type_id int          null,
    pay_type    varchar(100) null,
    constraint PayType_pk
        unique (pay_type_id)
)
    engine = InnoDB;

create table sailtotmdb.PickUpBase
(
    pick_up_code int        default 0 not null
        primary key,
    enable       tinyint(1) default 1 null
)
    engine = InnoDB;

create table sailtotmdb.ProgressDetailsInfo
(
    progress_id          int   not null
        primary key,
    new_users_number     int   not null,
    new_orders_number    int   not null,
    active_users_number  int   not null,
    search_number        int   not null,
    search_items_number  int   not null,
    new_membership_users int   not null,
    total_order_sum      float not null,
    created_date         date  not null
)
    engine = InnoDB;

create table sailtotmdb.RoleType
(
    role_id int         not null
        primary key,
    role    varchar(50) not null
)
    engine = InnoDB;

create table sailtotmdb.SearchItems
(
    search_id      int auto_increment,
    user_id        int          not null,
    search         varchar(100) not null,
    searched_times int          not null,
    created_date   datetime     not null,
    updated_date   datetime     not null,
    primary key (search_id, user_id)
)
    engine = InnoDB;

create index fk_SearchItems_Users1_idx
    on sailtotmdb.SearchItems (user_id);

create table sailtotmdb.StoreLocations
(
    location_id int auto_increment
        primary key,
    location    varchar(250)  not null,
    address     varchar(1000) null,
    contact     varchar(1000) null,
    worktime    varchar(1000) null
)
    engine = InnoDB;

create table sailtotmdb.Stuff
(
    stuff_id     int auto_increment,
    role_id      int          not null,
    location_id  int          not null,
    status_id    int          not null,
    first_name   varchar(50)  not null,
    last_name    varchar(50)  not null,
    email        varchar(250) not null,
    phone_number varchar(15)  not null,
    password     varchar(250) not null,
    created_date datetime     not null,
    resign_date  datetime     null,
    salary       float        not null,
    primary key (stuff_id, role_id, location_id, status_id)
)
    engine = InnoDB;

create index fk_Stuff_RoleType1_idx
    on sailtotmdb.Stuff (role_id);

create index fk_Stuff_StoreLocations1_idx
    on sailtotmdb.Stuff (location_id);

create index fk_Stuff_Stuff_status1_idx
    on sailtotmdb.Stuff (status_id);

create table sailtotmdb.StuffStatusType
(
    status_id int         not null
        primary key,
    status    varchar(50) not null
)
    engine = InnoDB;

create table sailtotmdb.Stuff_history
(
    history_id   int auto_increment,
    stuff_id     int          not null,
    role_id      int          not null,
    location_id  int          not null,
    status_id    int          not null,
    first_name   varchar(50)  not null,
    last_name    varchar(50)  not null,
    email        varchar(250) not null,
    phone_number varchar(15)  not null,
    password     varchar(250) not null,
    created_date datetime     not null,
    resign_date  datetime     not null,
    salary       float        not null,
    primary key (history_id, stuff_id)
)
    engine = InnoDB;

create index fk_Stuff_history_Stuff1_idx
    on sailtotmdb.Stuff_history (stuff_id);

create table sailtotmdb.TranslatorDict
(
    translator_id int auto_increment
        primary key,
    zh            varchar(3000) null,
    ru            varchar(3000) null
)
    engine = InnoDB;

create index TranslatorDict_ru_index
    on sailtotmdb.TranslatorDict (ru(255));

create index TranslatorDict_translator_id_index
    on sailtotmdb.TranslatorDict (translator_id);

create index TranslatorDict_zh_index
    on sailtotmdb.TranslatorDict (zh(255));

create table sailtotmdb.TransportDetails
(
    transport_detail_id      int auto_increment
        primary key,
    stuff_id                 int           null,
    carrier_id               int           null,
    transport_id             int           null,
    location_id              int           null,
    width                    int           null,
    height                   int           null,
    length                   int           null,
    weight                   float         null,
    estimated_transport_cost float         null,
    transport_cost           float         null,
    transport_status         int default 0 not null,
    transport_cost_paid_date datetime      null,
    measure_date             datetime      null
)
    engine = InnoDB;

create index TransportDetails_TransportStatusType_transport_status_type_id_fk
    on sailtotmdb.TransportDetails (transport_status);

create index TransportDetails_TransportTypes_transport_id_fk
    on sailtotmdb.TransportDetails (transport_id);

create index fk_TransportDetails_CarrierInfo1_idx
    on sailtotmdb.TransportDetails (carrier_id, transport_id);

create index fk_TransportDetails_StoreLocations1_idx
    on sailtotmdb.TransportDetails (location_id);

create index fk_TransportDetails_Stuff1_idx
    on sailtotmdb.TransportDetails (stuff_id);

create table sailtotmdb.TransportTypes
(
    transport_id int auto_increment
        primary key,
    transport    varchar(250) not null,
    price        float        not null,
    updated_date datetime     not null
)
    engine = InnoDB;

create table sailtotmdb.Users
(
    user_id       int auto_increment
        primary key,
    password      varchar(250)         not null,
    first_name    varchar(50)          not null,
    last_name     varchar(50)          not null,
    email         varchar(250)         not null,
    phone_number  varchar(45)          null,
    date_of_birth datetime             null,
    created_date  datetime             not null,
    updated_date  datetime             not null,
    user_name     varchar(500)         not null,
    enable        tinyint(1) default 0 not null
)
    engine = InnoDB;

