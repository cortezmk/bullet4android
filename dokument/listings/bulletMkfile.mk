LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := LinearMath
LOCAL_SRC_FILES := LinearMath/btAlignedAllocator.cpp \
LinearMath/btConvexHull.cpp \
LinearMath/btGeometryUtil.cpp \
LinearMath/btQuickprof.cpp \
LinearMath/btSerializer.cpp \

LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/LinearMath
include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := BulletCollision
LOCAL_SRC_FILES := 	BulletCollision/BroadphaseCollision/btAxisSweep3.cpp \
BulletCollision/BroadphaseCollision/btBroadphaseProxy.cpp \
BulletCollision/BroadphaseCollision/btCollisionAlgorithm.cpp \
BulletCollision/BroadphaseCollision/btDbvt.cpp \
BulletCollision/BroadphaseCollision/btDbvtBroadphase.cpp \
BulletCollision/BroadphaseCollision/btDispatcher.cpp \
BulletCollision/BroadphaseCollision/btMultiSapBroadphase.cpp \
BulletCollision/BroadphaseCollision/btOverlappingPairCache.cpp \
BulletCollision/BroadphaseCollision/btQuantizedBvh.cpp \
BulletCollision/BroadphaseCollision/btSimpleBroadphase.cpp \
BulletCollision/CollisionDispatch/btActivatingCollisionAlgorithm.cpp \
BulletCollision/CollisionDispatch/btBoxBoxCollisionAlgorithm.cpp \
BulletCollision/CollisionDispatch/btBox2dBox2dCollisionAlgorithm.cpp \
BulletCollision/CollisionDispatch/btBoxBoxDetector.cpp \
BulletCollision/CollisionDispatch/btCollisionDispatcher.cpp \
BulletCollision/CollisionDispatch/btCollisionObject.cpp \
BulletCollision/CollisionDispatch/btCollisionWorld.cpp \
BulletCollision/CollisionDispatch/btCompoundCollisionAlgorithm.cpp \
BulletCollision/CollisionDispatch/btConvexConcaveCollisionAlgorithm.cpp \
BulletCollision/CollisionDispatch/btConvexConvexAlgorithm.cpp \
BulletCollision/CollisionDispatch/btConvexPlaneCollisionAlgorithm.cpp \
BulletCollision/CollisionDispatch/btConvex2dConvex2dAlgorithm.cpp \
BulletCollision/CollisionDispatch/btDefaultCollisionConfiguration.cpp \
BulletCollision/CollisionDispatch/btEmptyCollisionAlgorithm.cpp \
BulletCollision/CollisionDispatch/btGhostObject.cpp \
BulletCollision/CollisionDispatch/btInternalEdgeUtility.cpp \
BulletCollision/CollisionDispatch/btInternalEdgeUtility.h \
BulletCollision/CollisionDispatch/btManifoldResult.cpp \
BulletCollision/CollisionDispatch/btSimulationIslandManager.cpp \
BulletCollision/CollisionDispatch/btSphereBoxCollisionAlgorithm.cpp \
BulletCollision/CollisionDispatch/btSphereSphereCollisionAlgorithm.cpp \
BulletCollision/CollisionDispatch/btSphereTriangleCollisionAlgorithm.cpp \
BulletCollision/CollisionDispatch/btUnionFind.cpp \
BulletCollision/CollisionDispatch/SphereTriangleDetector.cpp \
BulletCollision/CollisionShapes/btBoxShape.cpp \
BulletCollision/CollisionShapes/btBox2dShape.cpp \
BulletCollision/CollisionShapes/btBvhTriangleMeshShape.cpp \
BulletCollision/CollisionShapes/btCapsuleShape.cpp \
BulletCollision/CollisionShapes/btCollisionShape.cpp \
BulletCollision/CollisionShapes/btCompoundShape.cpp \
BulletCollision/CollisionShapes/btConcaveShape.cpp \
BulletCollision/CollisionShapes/btConeShape.cpp \
BulletCollision/CollisionShapes/btConvexHullShape.cpp \
BulletCollision/CollisionShapes/btConvexInternalShape.cpp \
BulletCollision/CollisionShapes/btConvexPointCloudShape.cpp \
BulletCollision/CollisionShapes/btConvexShape.cpp \
BulletCollision/CollisionShapes/btConvex2dShape.cpp \
BulletCollision/CollisionShapes/btConvexTriangleMeshShape.cpp \
BulletCollision/CollisionShapes/btCylinderShape.cpp \
BulletCollision/CollisionShapes/btEmptyShape.cpp \
BulletCollision/CollisionShapes/btHeightfieldTerrainShape.cpp \
BulletCollision/CollisionShapes/btMinkowskiSumShape.cpp \
BulletCollision/CollisionShapes/btMultimaterialTriangleMeshShape.cpp \
BulletCollision/CollisionShapes/btMultiSphereShape.cpp \
BulletCollision/CollisionShapes/btOptimizedBvh.cpp \
BulletCollision/CollisionShapes/btPolyhedralConvexShape.cpp \
BulletCollision/CollisionShapes/btScaledBvhTriangleMeshShape.cpp \
BulletCollision/CollisionShapes/btShapeHull.cpp \
BulletCollision/CollisionShapes/btSphereShape.cpp \
BulletCollision/CollisionShapes/btStaticPlaneShape.cpp \
BulletCollision/CollisionShapes/btStridingMeshInterface.cpp \
BulletCollision/CollisionShapes/btTetrahedronShape.cpp \
BulletCollision/CollisionShapes/btTriangleBuffer.cpp \
BulletCollision/CollisionShapes/btTriangleCallback.cpp \
BulletCollision/CollisionShapes/btTriangleIndexVertexArray.cpp \
BulletCollision/CollisionShapes/btTriangleIndexVertexMaterialArray.cpp \
BulletCollision/CollisionShapes/btTriangleMesh.cpp \
BulletCollision/CollisionShapes/btTriangleMeshShape.cpp \
BulletCollision/CollisionShapes/btUniformScalingShape.cpp \
BulletCollision/Gimpact/btContactProcessing.cpp \
BulletCollision/Gimpact/btGenericPoolAllocator.cpp \
BulletCollision/Gimpact/btGImpactBvh.cpp \
BulletCollision/Gimpact/btGImpactCollisionAlgorithm.cpp \
BulletCollision/Gimpact/btGImpactQuantizedBvh.cpp \
BulletCollision/Gimpact/btGImpactShape.cpp \
BulletCollision/Gimpact/btTriangleShapeEx.cpp \
BulletCollision/Gimpact/gim_box_set.cpp \
BulletCollision/Gimpact/gim_contact.cpp \
BulletCollision/Gimpact/gim_memory.cpp \
BulletCollision/Gimpact/gim_tri_collision.cpp \
BulletCollision/NarrowPhaseCollision/btContinuousConvexCollision.cpp \
BulletCollision/NarrowPhaseCollision/btConvexCast.cpp \
BulletCollision/NarrowPhaseCollision/btGjkConvexCast.cpp \
BulletCollision/NarrowPhaseCollision/btGjkEpa2.cpp \
BulletCollision/NarrowPhaseCollision/btGjkEpaPenetrationDepthSolver.cpp \
BulletCollision/NarrowPhaseCollision/btGjkPairDetector.cpp \
BulletCollision/NarrowPhaseCollision/btMinkowskiPenetrationDepthSolver.cpp \
BulletCollision/NarrowPhaseCollision/btPersistentManifold.cpp \
BulletCollision/NarrowPhaseCollision/btRaycastCallback.cpp \
BulletCollision/NarrowPhaseCollision/btSubSimplexConvexCast.cpp \
BulletCollision/NarrowPhaseCollision/btVoronoiSimplexSolver.cpp \

LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/BulletCollision
LOCAL_SHARED_LIBRARIES := LinearMath
include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := BulletDynamics
LOCAL_SRC_FILES := 	BulletDynamics/Character/btKinematicCharacterController.cpp \
BulletDynamics/ConstraintSolver/btConeTwistConstraint.cpp \
BulletDynamics/ConstraintSolver/btContactConstraint.cpp \
BulletDynamics/ConstraintSolver/btGeneric6DofConstraint.cpp \
BulletDynamics/ConstraintSolver/btGeneric6DofSpringConstraint.cpp \
BulletDynamics/ConstraintSolver/btHinge2Constraint.cpp \
BulletDynamics/ConstraintSolver/btHingeConstraint.cpp \
BulletDynamics/ConstraintSolver/btPoint2PointConstraint.cpp \
BulletDynamics/ConstraintSolver/btSequentialImpulseConstraintSolver.cpp \
BulletDynamics/ConstraintSolver/btSliderConstraint.cpp \
BulletDynamics/ConstraintSolver/btSolve2LinearConstraint.cpp \
BulletDynamics/ConstraintSolver/btTypedConstraint.cpp \
BulletDynamics/ConstraintSolver/btUniversalConstraint.cpp \
BulletDynamics/Dynamics/btContinuousDynamicsWorld.cpp \
BulletDynamics/Dynamics/btDiscreteDynamicsWorld.cpp \
BulletDynamics/Dynamics/btRigidBody.cpp \
BulletDynamics/Dynamics/btSimpleDynamicsWorld.cpp \
BulletDynamics/Dynamics/Bullet-C-API.cpp \
BulletDynamics/Vehicle/btRaycastVehicle.cpp \
BulletDynamics/Vehicle/btWheelInfo.cpp \

LOCAL_SHARED_LIBRARIES := BulletCollision \
LinearMath \

LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/BulletDynamics
include $(BUILD_SHARED_LIBRARY)