/* 사용된 SQL 문 저장 */

-- 회원 테이블 생성
drop table if exists member CASCADE;
create table member
(
    id bigint generated by default as identity,
    name varchar(255),
    primary key (id)
);
