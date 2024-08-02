-- 코드를 입력하세요
SELECT concat('/home/grep/src/', concat(b.board_id, concat('/', concat(f.FILE_ID, concat(f.file_name, f.file_ext))))) as FILE_PATH
from USED_GOODS_BOARD b 
join USED_GOODS_FILE f on b.board_id = f.board_id
where 1=1
    and b.BOARD_ID = (
        SELECT b.board_id
        from USED_GOODS_BOARD b 
        group by b.views
        order by b.VIEWS desc
        limit 1
    )
order by f.FILE_ID desc
;

# SELECT *
# from USED_GOODS_BOARD b 
# join USED_GOODS_FILE f on b.board_id = f.board_id
# order by b.VIEWS desc
# ;


# USED_GOODS_BOARD와 USED_GOODS_FILE 테이블에서 
# 조회수가 가장 높은 중고거래 게시물에 대한 첨부파일 경로를 조회하는 SQL문을 작성해주세요. 
# order by b.VIEWS desc

# 첨부파일 경로는 FILE ID를 기준으로 내림차순 정렬해주세요. 
# 기본적인 파일경로는 /home/grep/src/ 이며, 게시글 ID를 기준으로 디렉토리가 구분되고, 파일이름은 파일 ID, 파일 이름, 파일 확장자로 구성되도록 출력해주세요. 

# 조회수가 가장 높은 게시물은 하나만 존재합니다