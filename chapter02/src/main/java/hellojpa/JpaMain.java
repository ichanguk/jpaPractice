package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /* 멤버 저장
            Member member = new Member();
            member.setId(1L);
            member.setName("HelloA");

            em.persist(member);
            */

            /* 멤버 조회
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
            */

            /* 멤버 삭제
            Member findMember = em.find(Member.class, 1L);
            em.remove(findMember);
            */

            /*
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("helloJPA");
            */

            // 멤버 조회
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .getResultList();
            for (Member member : result) {
                System.out.println("member = " + member.getName());
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback(); // 잘못된 경우 롤백
        } finally {
            em.close(); // 매니저 닫기
        }

        emf.close(); // 팩토리 닫기
    }
}
