#!/bin/sh

COMMIT_MSG_FILE=$1

#커밋 메시지중 1번 라인을 얻는다.
FIRST_LINE=`head -n1 ${COMMIT_MSG_FILE}`


if [[ $FIRST_LINE =~ ^(Merge branch) ]] ||
   [[ $FIRST_LINE =~ ^(Merge pull request) ]]; then

  echo "Automatically generated commit message from git"
  exit 0
fi

if [[ $FIRST_LINE =~ ^(initial) ]]; then
  echo "Initial commit"
  exit 0
fi

if [[ $FIRST_LINE == "" ]]; then
  echo "empty commit message"
  exit 1
fi


if [[ $FIRST_LINE =~ (\.)$ ]]; then
  echo "\033[31;1m[commit lint err] 문장 마지막의 ('.') 마침표를 제거"
  exit 1

elif [[ ! $FIRST_LINE =~ ^(feat(\(.*\))?!?: ) ]] &&
    [[ ! $FIRST_LINE =~ ^(fix(\(.*\))?!?: ) ]] &&
    [[ ! $FIRST_LINE =~ ^(docs(\(.*\))?!?: ) ]] &&
    [[ ! $FIRST_LINE =~ ^(style(\(.*\))?!?: ) ]] &&
    [[ ! $FIRST_LINE =~ ^(refactor(\(.*\))?!?: ) ]] &&
    [[ ! $FIRST_LINE =~ ^(test(\(.*\))?!?: ) ]] &&
    [[ ! $FIRST_LINE =~ ^(chore(\(.*\))?!?: ) ]]; then

  echo "\n\033[31m[commit lint err] HEADER 의 접두사, 콜론, 띄어쓰기 형태를 확인\n"

  echo "\033[35m<type>(scope option): <subject>"
  echo "- feat: 새로운 기능 추가"
  echo "- fix: 버그 수정"
  echo "- docs: 문서의 수정"
  echo "- style: (코드의 수정 없이) 스타일(style)만 변경(들여쓰기 같은 포맷이나 세미콜론을 빼먹은 경우)"
  echo "- refactor: 코드를 리펙토링"
  echo "- test: Test 관련한 코드의 추가, 수정"
  echo "- chore: (코드의 수정 없이) 설정을 변경"
  exit 1

else
  echo "Pass commit lint!"
  exit 0
fi


exit 0