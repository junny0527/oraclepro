package com.javaex.phone;

import java.util.ArrayList;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// switch 에서는 변수가 공유 되므로 switch문 안에서 변수 선언하지 않고 상단에서 선언한다.

		int menu = 0;

		int PersonId = 0;

		String PersonName = "";

		String PersonHp = "";

		String PersonCompany = "";

		String search = "";

		PhoneDao dao = null;

		PersonVo vo = null;

		boolean result = false;

		System.out.println("===============전화번호관리프로그램===============");

		while (true) {

			System.out.println("1.등록 2.리스트 3.검색 4.수정 5.삭제 6.종료");

			System.out.print("메뉴 선택 >> ");

			menu = sc.nextInt();

			switch (menu) {

			case 1: // 등록

				System.out.println(menu + "번 선택");

				System.out.println("등록할 사람의 정보를 입력하시오.");

				System.out.print("이름 : ");

				PersonName = sc.next();

				System.out.print("핸드폰 : ");

				PersonHp = sc.next();

				System.out.print("회사전화번호 : ");

				PersonCompany = sc.next();

			
				dao = new PhoneDao();

				result = dao.PersonInsert(PersonName, PersonHp, PersonCompany);

				if (result == true) {

					System.out.println("등록 성공");

				} else {

					System.out.println("등록 실팽");

				}

				System.out.println();

				break;

			case 2: // 전체 조회

				System.out.println(menu + "번 선택");

				// 전체  조회

				System.out.println("===============리스트===============");

				dao = new PhoneDao();

				ArrayList<PersonVo> al = dao.personSelect();

				System.out.print("번호\t");

				System.out.print("이름\t");

				System.out.print("핸드폰\t");

				System.out.print("회사\t\t");


				System.out.println();

				for (int i = 0; i < al.size(); i++) {

					vo = al.get(i);

					System.out.print(vo.getPersonId() + "\t");

					System.out.print(vo.getPersonName() + "\t");

					System.out.print(vo.getPersonHp() + "\t");

					System.out.print(vo.getPersonCompany() + "\t\t");

					System.out.println();

				}

				System.out.println();

				break;

			case 3: // 조회

				System.out.println(menu + "번 선택");

				System.out.print("이름 입력 : ");

				search = sc.next();

				System.out.println("=============== " + search + " ===============");

				dao = new PhoneDao();

				vo = dao.persononeSelect(search);

				if (vo.getPersonId() > 0) {

					System.out.print(vo.getPersonId() + "\t");

					System.out.print(vo.getPersonName() + "\t");

					System.out.print(vo.getPersonHp() + "\t");

					System.out.print(vo.getPersonCompany() + "\t");

				

					System.out.println();

				} else {

					System.out.println("등록된 정보가 없습니다.");

				}

				System.out.println("=============== ======== ===============");

				System.out.println();

				break;

			case 4: // 정보수정

				System.out.println(menu + "번 선택");

				System.out.print("변경할 이름 : ");

				search = sc.next();

				dao = new PhoneDao();

				vo = dao.persononeSelect(search);

				if (vo.getPersonId() > 0) {

					PersonId = vo.getPersonId();

					PersonName = vo.getPersonName();
					
					PersonHp = vo.getPersonHp();

					PersonCompany = vo.getPersonCompany();

					// 뭘 변경할건지 물어볼꺼야.

					System.out.print("변경하실 내용 : 선택 [1]이름, [2]연락처, [3]화사번호, [4]전체변경 :");

					int sel = sc.nextInt();

					if (sel == 1) {

						System.out.print("변경할 이름 : ");

						PersonName = sc.next();

					} else if (sel == 2) {

						System.out.print("변경할 연락처 : ");

						PersonHp = sc.next();
					} else if (sel == 3) {

						System.out.print("변경할 회사연락처 : ");

						PersonCompany = sc.next();

					} else if (sel == 4) {

						System.out.print("변경할 이름 : ");

						PersonName = sc.next();



						System.out.print("변경할 연락처 : ");

						PersonHp = sc.next();

						System.out.print("변경할 회사연락처 : ");

						PersonCompany = sc.next();

					}

					dao = new PhoneDao();

					result = dao.personUpdate(PersonId, PersonName,PersonHp,PersonCompany);

					if (result == true) {

						System.out.println("등록 성공");

					} else {

						System.out.println("등록 실패");

					}

				} else {

					System.out.println("등록된 정보가 없습니다.");

				}

				System.out.println();

				break;

			case 5: // 학생정보삭제

				System.out.println(menu + "번 선택");

				System.out.print("삭제할 이름을 입력하세요 : ");

				search = sc.next();

				dao = new PhoneDao();

				vo = dao.persononeSelect(search);

				if (vo.getPersonId() > 0) {

					PersonId = vo.getPersonId();

					PersonName = vo.getPersonName();

					PersonHp = vo.getPersonHp();

					PersonCompany = vo.getPersonCompany();

					dao = new PhoneDao();

					result = dao.personDelete(PersonId);

					if (result == true) {

						System.out.println("삭제 성공");

					} else {

						System.out.println("삭제 실패");

					}

				} else {

					System.out.println("등록된 정보가 없습니다.");

				}

				System.out.println();

				break;

			case 6: // 프로그램 종료

				System.out.println(menu + "번 선택");

				System.out.println();

				break;

			}

			if (menu == 6) {

				System.out.println("프로그램종료");

				break;

			}

		}

		sc.close();
	}
}