#!/bin/sh

COMMIT_MESSAGE=$1

addCommitFooterTicket() {
	#git branch 명령을 수행하여 그중 * 이 있는 부분 즉 지금의 브랜치 명 확인
  NAME=$(git branch | grep '*' | sed 's/* //')

	#해당 브랜치 명에서 / 를 기준으로 문자열을 자르는데 그중 2번째것을 얻는다.
  ISSUE_NUMBER=`echo $NAME | cut -d '/' -f2`
  DESCRIPTION=$(git config branch."$NAME".description)

	# 잘라낸 문자열 ISSUE_NUMBER 이 ISSUE 혹은 TIKET 문자열을 포함하고 있다면 커밋 에디터에 추가
  if [[ $ISSUE_NUMBER == *ISSUE* ]] || [[ $ISSUE_NUMBER == *TIKET* ]]; then
    echo "ticket: $ISSUE_NUMBER $(cat $COMMIT_MESSAGE)" > $COMMIT_MESSAGE
  fi

  if [ -n "$DESCRIPTION" ]
  then
     echo "" >> $COMMIT_MESSAGE
     echo $DESCRIPTION >> $COMMIT_MESSAGE
  fi
}

MERGE=$(cat $COMMIT_MESSAGE|grep -i 'merge'|wc -l)

if [ $MERGE -eq 0 ] ; then
  addCommitFooterTicket
fi